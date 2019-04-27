package by.tomal.sw.data.repository

import android.content.Context
import by.tomal.sw.data.db.AppDatabase
import by.tomal.sw.data.entity.MonsterGuideResponse
import by.tomal.sw.data.entity.MonsterListResponse
import by.tomal.sw.data.entity.guideResponseToEntity
import by.tomal.sw.data.rest.GuideService
import by.tomal.sw.domain.entity.MonsterGuide
import by.tomal.sw.domain.entity.exception.AppException
import by.tomal.sw.domain.entity.exception.AppExceptionType
import by.tomal.sw.domain.repository.MonsterGuideRepository
import io.reactivex.Single
import java.net.SocketTimeoutException

class MonsterGuideRepository(
    private val awakenName: String,
    private val service: GuideService,
    context: Context
) : MonsterGuideRepository {

    private val guideDao = AppDatabase.create(context).getGuideDao()

    override fun fetch(): Single<MonsterGuide> {
        val response: Single<MonsterGuideResponse> = guideDao.getMonsterByAwakenName(awakenName)
        return response
            .flatMap {
                Single.just(it)
            }
            .map {
                it.guideResponseToEntity()
            }.onErrorResumeNext {
                when (it) {
                    is SocketTimeoutException -> {
                        Single.error(AppException(AppExceptionType.NO_INTERNET))
                    }
                    else -> {
                        Single.error(AppException(AppExceptionType.UNKNOWN))
                    }
                }
            }
    }
}