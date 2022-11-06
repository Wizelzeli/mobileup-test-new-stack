package ru.mobileup.template.features.crypto.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mobileup.template.features.crypto.domain.CryptoDetails

@Serializable
class DetailedCryptoResponse(
    @SerialName("name") val name: String,
    @SerialName("categories") val categories: List<String>,
    @SerialName("description") val description: Describe,
    @SerialName("image") val image: Img
) {
    @Serializable
    class Describe(@SerialName("en") val en: String)

    @Serializable
    class Img(@SerialName("large") val large: String)
}

fun DetailedCryptoResponse.toDomain(): CryptoDetails {
    return CryptoDetails(
        name = name,
        categories = categories,
        description = description.en,
        image = image.large
    )
}
