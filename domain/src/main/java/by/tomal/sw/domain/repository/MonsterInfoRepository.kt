package by.tomal.sw.domain.repository

import by.tomal.sw.domain.entity.MonsterInfo
import io.reactivex.Single

interface MonsterInfoRepository {
    fun fetch() : Single<MonsterInfo>
}