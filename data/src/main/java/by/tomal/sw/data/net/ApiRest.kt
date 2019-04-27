package by.tomal.sw.data.net

import by.tomal.sw.data.entity.MonsterGuideResponse
import by.tomal.sw.data.entity.MonsterListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiRest {
    @GET("monsters")
    fun getMonsters(
    ): Single<List<MonsterListResponse>>

    @GET("guides")
    fun getGuides(
    ): Single<List<MonsterGuideResponse>>
}