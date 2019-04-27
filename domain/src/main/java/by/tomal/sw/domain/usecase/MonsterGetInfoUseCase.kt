package by.tomal.sw.domain.usecase

import by.tomal.sw.domain.entity.MonsterInfo
import io.reactivex.Single

interface MonsterGetInfoUseCase {
    fun getMonsterInfo(): Single<MonsterInfo>
}