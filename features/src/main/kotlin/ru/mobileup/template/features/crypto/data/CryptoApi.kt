package ru.mobileup.template.features.crypto.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.mobileup.template.features.crypto.data.dto.CryptoResponse
import ru.mobileup.template.features.crypto.data.dto.DetailedCryptoResponse

interface CryptoApi {

    @GET("coins/markets")
    suspend fun getCryptosByValue(@Query("vs_currency") currency: String): List<CryptoResponse>

    @GET("coins/{id}?localization=false&tickers=false&market_data=false&community_data=false&" +
            "developer_data=false&sparkline=false")
    suspend fun getDetailedCryptoById(@Path("id") id: String): DetailedCryptoResponse
}