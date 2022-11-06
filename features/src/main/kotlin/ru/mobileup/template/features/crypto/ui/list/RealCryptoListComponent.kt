package ru.mobileup.template.features.crypto.ui.list

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import kotlinx.parcelize.Parcelize
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed.keepPreviousData
import me.aartikov.replica.single.Loadable
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.core.utils.persistent
import ru.mobileup.template.features.crypto.domain.Crypto
import ru.mobileup.template.features.crypto.domain.CryptoId
import ru.mobileup.template.features.crypto.domain.CryptoValue
import ru.mobileup.template.features.crypto.domain.CryptoValueName

class RealCryptoListComponent(
    componentContext: ComponentContext,
    private val onOutput: (CryptoListComponent.Output) -> Unit,
    private val cryptosByValueReplica: KeyedReplica<CryptoValueName, List<Crypto>>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, CryptoListComponent{

    override val values: List<CryptoValue> = listOf(
        CryptoValue.Eur,
        CryptoValue.Usd
    )

    override var selectedValue by mutableStateOf(values[0].name)

    override val cryptosState by cryptosByValueReplica
        .keepPreviousData()
        .observe(
            lifecycle,
            errorHandler,
            key = { selectedValue }
        )

    init {
        persistent(
            save = { PersistentState(selectedValue) },
            restore = { state -> selectedValue = state.selectedValue }
        )
    }

    @Parcelize
    private data class PersistentState(
        val selectedValue: CryptoValueName
    ) : Parcelable

    override fun onValueClick(cryptoValueName: CryptoValueName) {
        selectedValue = cryptoValueName
    }

    override fun onCryptoClick(cryptoId: CryptoId) {
        onOutput(CryptoListComponent.Output.CryptoDetailsRequested(cryptoId))
    }

    override fun onRetryClick() {
        cryptosByValueReplica.refresh(selectedValue)
    }

    override fun onRefresh() {
        cryptosByValueReplica.refresh(selectedValue)
    }
}