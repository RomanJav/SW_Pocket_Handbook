package by.tomal.sw.data.net

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object MonsterHttpClientBuilder {

    fun getHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(5, TimeUnit.SECONDS)
        return clientBuilder.build()
    }
}