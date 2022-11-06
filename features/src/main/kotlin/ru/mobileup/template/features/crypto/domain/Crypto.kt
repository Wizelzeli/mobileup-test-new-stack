package ru.mobileup.template.features.crypto.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class CryptoId(val value: String) : Parcelable

data class Crypto(
    val id: CryptoId,
    val image: String,
    val name: String,
    val currentPrice: Double,
    val priceChangePercentage: Double,
    val symbol: String
)