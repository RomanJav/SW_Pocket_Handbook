package by.tomal.sw.domain.usecase

import by.tomal.sw.domain.entity.Monster
import by.tomal.sw.domain.repository.MonsterRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class MonsterGetUseCaseDefault(
    private val workScheduler: Scheduler,
    private val postScheduler: Scheduler,
    private val monsterRepository: MonsterRepository
) : MonsterGetUseCase {

    override fun get(): Single<List<Monster>> {
        return monsterRepository.fetch()
            .subscribeOn(workScheduler)
            .observeOn(postScheduler)
    }
}