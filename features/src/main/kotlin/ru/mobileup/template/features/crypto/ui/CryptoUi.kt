
package ru.mobileup.template.features.crypto.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.utils.createFakeChildStack
import ru.mobileup.template.features.crypto.ui.details.CryptoDetailsUi
import ru.mobileup.template.features.crypto.ui.list.CryptoListUi
import ru.mobileup.template.features.crypto.ui.list.FakeCryptoListComponent

@Composable
fun CryptoUi(
    component: CryptoComponent,
    modifier: Modifier = Modifier
) {
    Children(component.childStack, modifier) { child ->
        when (val instance = child.instance) {
            is CryptoComponent.Child.List -> CryptoListUi(instance.component)
            is CryptoComponent.Child.Details -> CryptoDetailsUi(instance.component)
        }
    }
}

@Preview
@Composable
fun CryptoUiPreview() {
    AppTheme {
        CryptoUi(FakeCryptoComponent())
    }
}

class FakeCryptoComponent : CryptoComponent {

    override val childStack = createFakeChildStack(
        CryptoComponent.Child.List(FakeCryptoListComponent())
    )
}
