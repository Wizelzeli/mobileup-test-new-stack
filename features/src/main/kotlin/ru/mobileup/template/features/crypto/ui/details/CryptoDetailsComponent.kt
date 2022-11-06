package ru.mobileup.template.features.crypto.ui.details

import me.aartikov.replica.single.Loadable
import ru.mobileup.template.features.crypto.domain.CryptoDetails

interface CryptoDetailsComponent {

    val cryptoDetailsState: Loadable<CryptoDetails>

    fun onRetryClick()

    fun onBackPressed()

    fun onRefresh()

    sealed interface Output {
        object BackButtonPressed : Output
    }
}