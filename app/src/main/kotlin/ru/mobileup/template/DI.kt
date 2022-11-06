package ru.mobileup.template


import ru.mobileup.template.core.coreModule
import ru.mobileup.template.features.crypto.cryptoModule

val allModules = listOf(
    coreModule(BuildConfig.BACKEND_URL),
    cryptoModule
)
