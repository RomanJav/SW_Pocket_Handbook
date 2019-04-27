package by.tomal.sw.domain.usecase

import by.tomal.sw.domain.entity.MonsterGuide
import io.reactivex.Single

interface MonsterGetGuideUseCase {
    fun getMonsterGuide(): Single<MonsterGuide>
}