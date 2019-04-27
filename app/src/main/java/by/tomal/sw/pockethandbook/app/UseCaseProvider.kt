package by.tomal.sw.pockethandbook.app

import by.tomal.sw.data.repository.MonsterInfoRepository
import by.tomal.sw.data.repository.MonsterRepositoryDefault
import by.tomal.sw.data.repository.MonsterByElementRepository
import by.tomal.sw.data.repository.MonsterGuideRepository
import by.tomal.sw.data.rest.GuideService
import by.tomal.sw.data.rest.MonsterService
import by.tomal.sw.domain.usecase.*
import by.tomal.sw.pockethandbook.BuildConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object UseCaseProvider {

    fun provideGetMonsterUseCase(): MonsterGetUseCase {
        return MonsterGetUseCaseDefault(
            getWorkScheduler(),
            getPostScheduler(),
            MonsterRepositoryDefault(
                BuildConfig.API_ENDPOINT,
                MonsterService(SWPocketHandbookApplication.instance.applicationContext),
                GuideService(SWPocketHandbookApplication.instance.applicationContext),
                SWPocketHandbookApplication.instance.applicationContext
            )
        )
    }
    fun provideGetMonsterUseCaseElementMonsters(element: String): MonsterGetUseCase {
        return MonsterGetUseCaseByElement(
            getWorkScheduler(),
            getPostScheduler(),
            MonsterByElementRepository(
                element,
                MonsterService(SWPocketHandbookApplication.instance.applicationContext),
                SWPocketHandbookApplication.instance.applicationContext
            )
        )
    }
    fun provideGetMonsterInfoUseCaseByName(awakenName: String): MonsterGetInfoUseCase {
        return MonsterGetInfoUseCaseByName(
            getWorkScheduler(),
            getPostScheduler(),
            MonsterInfoRepository(
                awakenName,
                SWPocketHandbookApplication.instance.applicationContext
            )
        )
    }
    fun provideGetMonsterGuideUseCaseByName(awakenName: String): MonsterGetGuideUseCase {
        return MonsterGetGuideUseCaseByName(
            getWorkScheduler(),
            getPostScheduler(),
            MonsterGuideRepository(
                awakenName,
                GuideService(SWPocketHandbookApplication.instance.applicationContext),
                SWPocketHandbookApplication.instance.applicationContext
            )
        )
    }

    private fun getWorkScheduler() = Schedulers.io()

    private fun getPostScheduler() = AndroidSchedulers.mainThread()

}