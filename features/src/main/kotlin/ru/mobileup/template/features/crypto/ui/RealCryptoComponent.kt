package ru.mobileup.template.features.crypto.ui

import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.utils.toComposeState
import ru.mobileup.template.features.crypto.createCryptoDetailsComponent
import ru.mobileup.template.features.crypto.createCryptoListComponent
import ru.mobileup.template.features.crypto.domain.CryptoDetails
import ru.mobileup.template.features.crypto.domain.CryptoId
import ru.mobileup.template.features.crypto.ui.details.CryptoDetailsComponent
import ru.mobileup.template.features.crypto.ui.list.CryptoListComponent


class RealCryptoComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, CryptoComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: ChildStack<*, CryptoComponent.Child> by childStack(
        source = navigation,
        initialConfiguration = ChildConfig.List,
        handleBackButton = true,
        childFactory = ::createChild
    ).toComposeState(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): CryptoComponent.Child = when (config) {
        is ChildConfig.List -> {
            CryptoComponent.Child.List(
                componentFactory.createCryptoListComponent(
                    componentContext,
                    ::onCryptoListOutput
                )
            )
        }

        is ChildConfig.Details -> {
            CryptoComponent.Child.Details(
                componentFactory.createCryptoDetailsComponent(
                    componentContext,
                    config.cryptoId,
                    ::onCryptoDetailsOutput
                )
            )
        }
    }

    private fun onCryptoListOutput(output: CryptoListComponent.Output) {
        when (output) {
            is CryptoListComponent.Output.CryptoDetailsRequested -> {
                navigation.push(ChildConfig.Details(output.cryptoId))
            }
        }
    }

    private fun onCryptoDetailsOutput(output: CryptoDetailsComponent.Output) {
        when (output) {
            is CryptoDetailsComponent.Output.BackButtonPressed -> {
                navigation.pop()
            }
        }
    }

    private sealed interface ChildConfig : Parcelable {

        @Parcelize
        object List : ChildConfig

        @Parcelize
        data class Details(val cryptoId: CryptoId) : ChildConfig
    }
}
