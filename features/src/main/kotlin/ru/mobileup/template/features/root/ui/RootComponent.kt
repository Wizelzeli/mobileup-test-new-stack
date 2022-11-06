package ru.mobileup.template.features.root.ui

import com.arkivanov.decompose.router.stack.ChildStack
import ru.mobileup.template.core.message.ui.MessageComponent
import ru.mobileup.template.features.crypto.ui.CryptoComponent

interface RootComponent {

    val childStack: ChildStack<*, Child>

    val messageComponent: MessageComponent

    sealed interface Child {
        class Crypto(val component: CryptoComponent) : Child
    }
}
