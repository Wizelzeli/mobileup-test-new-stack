package ru.mobileup.template.features.crypto.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mobileup.template.features.crypto.domain.Crypto
import ru.mobileup.template.features.crypto.domain.CryptoId

@Serializable
class CryptosByValueResponse(
    @SerialName("cryptos") val cryptos: List<CryptoWrapperResponse>
)

fun CryptosByValueResponse.toDomain(): List<Crypto> {
    return cryptos.map { it.crypto.toDomain() }
}


@Serializable
class CryptoResponse(
    @SerialName("current_price") val currentPrice: Double,
    @SerialName("id") val id: String,
    @SerialName("image") val image: String,
    @SerialName("name") val name: String,
    @SerialName("price_change_percentage_24h") val priceChangePercentage24h: Double,
    @SerialName("symbol") val symbol: String,
)

@Serializable
class CryptoWrapperResponse(
    @SerialName("crypto") val crypto: CryptoResponse
)

fun CryptoResponse.toDomain(): Crypto {
    return Crypto(
        id = CryptoId(id),
        image = image,
        name = name,
        currentPrice = currentPrice,
        priceChangePercentage = priceChangePercentage24h,
        symbol = symbol
    )
}

fun List<CryptoResponse>.toDomain(): List<Crypto> {
    return this.map { it.toDomain() }
}
