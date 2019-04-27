package by.tomal.sw.domain.usecase

import by.tomal.sw.domain.entity.Monster
import io.reactivex.Single

interface MonsterGetUseCase {
    fun get(): Single<List<Monster>>
}