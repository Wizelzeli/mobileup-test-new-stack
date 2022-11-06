package ru.mobileup.template.features.crypto.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class CryptoValueName(val value: String) : Parcelable

data class CryptoValue(val name: CryptoValueName) {
    companion object {
        val Usd = CryptoValue(name = CryptoValueName("USD"))
        val Eur = CryptoValue(name = CryptoValueName("EUR"))
    }
}