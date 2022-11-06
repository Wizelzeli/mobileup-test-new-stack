package ru.mobileup.template.features.crypto.ui

import com.arkivanov.decompose.router.stack.ChildStack
import ru.mobileup.template.features.crypto.ui.details.CryptoDetailsComponent

import ru.mobileup.template.features.crypto.ui.list.CryptoListComponent
import ru.mobileup.template.features.crypto.ui.list.FakeCryptoListComponent

interface CryptoComponent {

    val childStack: ChildStack<*, Child>

    sealed interface Child {
        class List(val component: CryptoListComponent) : Child
        class Details(val component: CryptoDetailsComponent) : Child
    }
}
