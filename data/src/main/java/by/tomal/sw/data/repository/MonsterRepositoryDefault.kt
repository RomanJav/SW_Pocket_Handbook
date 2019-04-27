package by.tomal.sw.data.repository

import android.content.Context
import by.tomal.sw.data.db.AppDatabase
import by.tomal.sw.data.entity.monsterListResponseToEntity
import by.tomal.sw.data.rest.GuideService
import by.tomal.sw.data.rest.MonsterService
import by.tomal.sw.domain.entity.Monster
import by.tomal.sw.domain.entity.exception.AppException
import by.tomal.sw.domain.entity.exception.AppExceptionType
import by.tomal.sw.domain.repository.MonsterRepository
import io.reactivex.Single
import java.net.SocketTimeoutException

class MonsterRepositoryDefault(
    private val url: String,
    private val serviceMonster: MonsterService,
    private val guideService: GuideService,
    context: Context
) : MonsterRepository {

    private val monsterDao = AppDatabase.create(context).getMonsterDao()
    override fun fetch(): Single<List<Monster>> {

        return monsterDao.get().flatMap {
            if (!it.isNullOrEmpty()) {
                Single.just(it)
            } else {
                serviceMonster.requestData().map { monsters -> monsters }
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
        }.doAfterSuccess { guideService.requestData() }
    }
}