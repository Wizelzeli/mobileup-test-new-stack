package ru.mobileup.template.features.crypto.data

import me.aartikov.replica.keyed.KeyedReplica
import ru.mobileup.template.features.crypto.domain.Crypto
import ru.mobileup.template.features.crypto.domain.CryptoDetails
import ru.mobileup.template.features.crypto.domain.CryptoId
import ru.mobileup.template.features.crypto.domain.CryptoValueName


interface CryptoRepository {

    val cryptosByValueReplica: KeyedReplica<CryptoValueName, List<Crypto>>

    val detailedCryptoByIdReplica: KeyedReplica<CryptoId, CryptoDetails>
}