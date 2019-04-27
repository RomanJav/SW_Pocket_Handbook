package by.tomal.sw.data.net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal fun monsterProvideApi(): ApiRest {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.backendless.com/6F5DF07E-FDF1-2BD3-FF19-4EEA42398B00/39FC19A7-6FB0-409E-FFC9-89CD05F24F00/data/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(MonsterHttpClientBuilder.getHttpClient())
        .build()

    return retrofit.create(ApiRest::class.java)
}