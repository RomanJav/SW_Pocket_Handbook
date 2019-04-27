package by.tomal.sw.data.rest

import android.content.Context
import by.tomal.sw.data.db.AppDatabase
import by.tomal.sw.data.entity.MonsterListResponse
import by.tomal.sw.data.net.monsterProvideApi
import io.reactivex.Single
import io.reactivex.disposables.Disposable

class MonsterService(context: Context) {

    private val monsterDao = AppDatabase.create(context).getMonsterDao()
    private val api = monsterProvideApi()
    private var disposable: Disposable? = null

    @Throws(Exception::class)
    fun requestData(): Single<List<MonsterListResponse>> {
        val monsterResponse = api.getMonsters()

        disposable = monsterResponse
            .subscribe { response ->
                run {
                    disposable?.let {
                        if (!it.isDisposed) {
                            it.dispose()
                        }
                    }
                }
                monsterDao.insert(response ?: arrayListOf())
            }

        return monsterResponse
    }
}