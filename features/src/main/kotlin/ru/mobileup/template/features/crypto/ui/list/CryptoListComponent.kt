package ru.mobileup.template.features.crypto.ui.list

import me.aartikov.replica.single.Loadable
import ru.mobileup.template.features.crypto.domain.Crypto
import ru.mobileup.template.features.crypto.domain.CryptoId
import ru.mobileup.template.features.crypto.domain.CryptoValue
import ru.mobileup.template.features.crypto.domain.CryptoValueName

interface CryptoListComponent {

    val values: List<CryptoValue>

    var selectedValue: CryptoValueName

    val cryptosState: Loadable<List<Crypto>>

    fun onValueClick(cryptoValueName: CryptoValueName)

    fun onCryptoClick(cryptoId: CryptoId)

    fun onRetryClick()

    fun onRefresh()

    sealed interface Output {
        data class CryptoDetailsRequested(val cryptoId: CryptoId) : Output
    }

}