package ru.mobileup.template.features.crypto.data

import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedPhysicalReplica
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.crypto.data.dto.toDomain
import ru.mobileup.template.features.crypto.domain.Crypto
import ru.mobileup.template.features.crypto.domain.CryptoDetails
import ru.mobileup.template.features.crypto.domain.CryptoId
import ru.mobileup.template.features.crypto.domain.CryptoValueName
import kotlin.time.Duration.Companion.seconds

class CryptoRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CryptoApi
) : CryptoRepository {

    override val cryptosByValueReplica: KeyedPhysicalReplica<CryptoValueName, List<Crypto>> =
        replicaClient.createKeyedReplica(
            name = "cryptosByValue",
            childName = { value -> "value = ${value.value}" },
            childSettings = {
                ReplicaSettings(
                    staleTime = 10.seconds,
                    clearTime = 60.seconds
                )
            }
        ) { cryptoValue ->
            api.getCryptosByValue(cryptoValue.value).toDomain()
        }

    override val detailedCryptoByIdReplica: KeyedPhysicalReplica<CryptoId, CryptoDetails> =
        replicaClient.createKeyedReplica(
            name = "detailedCryptoById",
            childName = { cryptoId -> "cryptoId = ${cryptoId.value}" },
            settings = KeyedReplicaSettings(maxCount = 5),
            childSettings = {
                ReplicaSettings(staleTime = 10.seconds)
            }
        ) { cryptoId ->
            api.getDetailedCryptoById(cryptoId.value).toDomain()
        }
}