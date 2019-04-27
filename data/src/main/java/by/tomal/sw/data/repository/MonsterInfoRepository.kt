package by.tomal.sw.data.repository

import android.content.Context
import by.tomal.sw.data.db.AppDatabase
import by.tomal.sw.data.entity.MonsterListResponse
import by.tomal.sw.data.entity.monsterInfoResponseToEntity
import by.tomal.sw.domain.entity.MonsterInfo
import by.tomal.sw.domain.entity.exception.AppException
import by.tomal.sw.domain.entity.exception.AppExceptionType
import by.tomal.sw.domain.repository.MonsterInfoRepository
import io.reactivex.Single
import java.net.SocketTimeoutException

class MonsterInfoRepository(
    private val awakenName: String,
    context: Context
) : MonsterInfoRepository {

    private val monsterDao = AppDatabase.create(context).getMonsterDao()

    override fun fetch(): Single<MonsterInfo> {
        val response: Single<MonsterListResponse> = monsterDao.getMonsterByAwakenName(awakenName)
        return response
            .flatMap { Single.just(it) }
            .map {
                it.monsterInfoResponseToEntity()
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