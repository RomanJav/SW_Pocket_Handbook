package by.tomal.sw.domain.repository

import by.tomal.sw.domain.entity.MonsterGuide
import io.reactivex.Single

interface MonsterGuideRepository {
    fun fetch() : Single<MonsterGuide>
}