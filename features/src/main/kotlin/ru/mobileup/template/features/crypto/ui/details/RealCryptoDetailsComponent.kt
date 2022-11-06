package ru.mobileup.template.features.crypto.ui.details

import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.keyed.KeyedReplica
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.crypto.domain.CryptoDetails
import ru.mobileup.template.features.crypto.domain.CryptoId

class RealCryptoDetailsComponent(
    private val componentContext: ComponentContext,
    private val cryptoId: CryptoId,
    private val onOutput: (CryptoDetailsComponent.Output) -> Unit,
    private val detailedCryptoByIdReplica: KeyedReplica<CryptoId, CryptoDetails>,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, CryptoDetailsComponent {

    override val cryptoDetailsState by detailedCryptoByIdReplica.observe(
        lifecycle,
        errorHandler,
        key = { cryptoId }
    )

    override fun onRetryClick() {
        detailedCryptoByIdReplica.refresh(cryptoId)
    }

    override fun onBackPressed() {
        onOutput(CryptoDetailsComponent.Output.BackButtonPressed)
    }

    override fun onRefresh() {
        detailedCryptoByIdReplica.refresh(cryptoId)
    }

}