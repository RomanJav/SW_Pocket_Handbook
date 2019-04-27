package by.tomal.sw.data.repository

import android.content.Context
import by.tomal.sw.data.db.AppDatabase
import by.tomal.sw.data.entity.DataConstants.DARK
import by.tomal.sw.data.entity.DataConstants.FIRE
import by.tomal.sw.data.entity.DataConstants.LIGHT
import by.tomal.sw.data.entity.DataConstants.WATER
import by.tomal.sw.data.entity.DataConstants.WIND
import by.tomal.sw.data.entity.MonsterListResponse
import by.tomal.sw.data.entity.monsterListResponseToEntity
import by.tomal.sw.data.rest.MonsterService
import by.tomal.sw.domain.entity.Monster
import by.tomal.sw.domain.entity.exception.AppException
import by.tomal.sw.domain.entity.exception.AppExceptionType
import by.tomal.sw.domain.repository.MonsterRepository
import io.reactivex.Single
import java.net.SocketTimeoutException

class MonsterByElementRepository(
    private val element: String,
    private val service: MonsterService,
    context: Context
) : MonsterRepository {

    private val monsterDao = AppDatabase.create(context).getMonsterDao()

    override fun fetch(): Single<List<Monster>> {
        var response: Single<List<MonsterListResponse>> = monsterDao.get()
        when (element) {
            FIRE -> response = monsterDao.getMonstersByElement("Fire")
            WIND -> response = monsterDao.getMonstersByElement("Wind")
            WATER -> response = monsterDao.getMonstersByElement("Water")
            LIGHT -> response = monsterDao.getMonstersByElement("Light")
            DARK -> response = monsterDao.getMonstersByElement("Dark")
        }
        return response
            .flatMap {
                if (!it.isNullOrEmpty()) {
                    Single.just(it)
                } else {
                    service.requestData().map { monsters -> monsters }
                }
            }.map { monsterResponse ->
                monsterResponse.map { it.monsterListResponseToEntity() }
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