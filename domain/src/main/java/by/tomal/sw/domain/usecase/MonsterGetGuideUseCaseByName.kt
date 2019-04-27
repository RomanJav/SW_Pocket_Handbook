package by.tomal.sw.domain.usecase

import by.tomal.sw.domain.entity.MonsterGuide
import by.tomal.sw.domain.repository.MonsterGuideRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class MonsterGetGuideUseCaseByName (
    private val workScheduler: Scheduler,
    private val postScheduler: Scheduler,
    private val monsterGuideRepository: MonsterGuideRepository
) : MonsterGetGuideUseCase {

    override fun getMonsterGuide(): Single<MonsterGuide> {
        return monsterGuideRepository.fetch()
            .subscribeOn(workScheduler)
            .observeOn(postScheduler)
    }
}