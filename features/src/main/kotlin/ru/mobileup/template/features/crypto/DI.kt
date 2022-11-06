package ru.mobileup.template.features.crypto

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.algebra.withKey
import me.aartikov.replica.keyed.KeyedPhysicalReplica
import org.koin.dsl.module
import org.koin.core.component.get
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.network.NetworkApiFactory
import ru.mobileup.template.features.crypto.data.CryptoApi
import ru.mobileup.template.features.crypto.data.CryptoRepository
import ru.mobileup.template.features.crypto.data.CryptoRepositoryImpl
import ru.mobileup.template.features.crypto.domain.Crypto
import ru.mobileup.template.features.crypto.domain.CryptoDetails
import ru.mobileup.template.features.crypto.domain.CryptoId
import ru.mobileup.template.features.crypto.ui.CryptoComponent
import ru.mobileup.template.features.crypto.ui.RealCryptoComponent
import ru.mobileup.template.features.crypto.ui.details.CryptoDetailsComponent
import ru.mobileup.template.features.crypto.ui.details.RealCryptoDetailsComponent
import ru.mobileup.template.features.crypto.ui.list.CryptoListComponent
import ru.mobileup.template.features.crypto.ui.list.RealCryptoListComponent


val cryptoModule = module {
    single<CryptoApi> { get<NetworkApiFactory>().createUnauthorizedApi() }
    single<CryptoRepository> { CryptoRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createCryptoComponent(
    componentContext: ComponentContext
): CryptoComponent {
    return RealCryptoComponent(componentContext, get())
}

fun ComponentFactory.createCryptoListComponent(
    componentContext: ComponentContext,
    onOutput: (CryptoListComponent.Output) -> Unit
): CryptoListComponent {
    val cryptosByValueReplica = get<CryptoRepository>().cryptosByValueReplica
    return RealCryptoListComponent(
        componentContext = componentContext,
        onOutput = onOutput,
        cryptosByValueReplica = cryptosByValueReplica,
        get()
    )
}

fun ComponentFactory.createCryptoDetailsComponent(
    componentContext: ComponentContext,
    cryptoId: CryptoId,
    onOutput: (CryptoDetailsComponent.Output) -> Unit
): CryptoDetailsComponent {
    val detailedCryptoByIdReplica = get<CryptoRepository>().detailedCryptoByIdReplica
    return RealCryptoDetailsComponent(
        componentContext = componentContext,
        cryptoId = cryptoId,
        onOutput = onOutput,
        detailedCryptoByIdReplica = detailedCryptoByIdReplica,
        get()
    )
}
