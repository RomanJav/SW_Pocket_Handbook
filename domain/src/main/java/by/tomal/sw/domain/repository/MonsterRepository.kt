package by.tomal.sw.domain.repository

import by.tomal.sw.domain.entity.Monster
import io.reactivex.Single

interface MonsterRepository {
    fun fetch() : Single<List<Monster>>
}