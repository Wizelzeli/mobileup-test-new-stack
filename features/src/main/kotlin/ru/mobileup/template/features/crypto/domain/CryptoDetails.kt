package ru.mobileup.template.features.crypto.domain

data class CryptoDetails(
    val name: String,
    val categories: List<String>,
    val description: String,
    val image: String
)