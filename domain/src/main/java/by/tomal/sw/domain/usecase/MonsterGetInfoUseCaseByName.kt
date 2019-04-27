package by.tomal.sw.domain.usecase

import by.tomal.sw.domain.entity.MonsterInfo
import by.tomal.sw.domain.repository.MonsterInfoRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class MonsterGetInfoUseCaseByName(
    private val workScheduler: Scheduler,
    private val postScheduler: Scheduler,
    private val monsterInfoRepository: MonsterInfoRepository
) : MonsterGetInfoUseCase {

    override fun getMonsterInfo(): Single<MonsterInfo> {
        return monsterInfoRepository.fetch()
            .subscribeOn(workScheduler)
            .observeOn(postScheduler)
    }
}