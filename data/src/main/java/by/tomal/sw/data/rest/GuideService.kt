package by.tomal.sw.data.rest

import android.content.Context
import by.tomal.sw.data.db.AppDatabase
import by.tomal.sw.data.entity.MonsterGuideResponse
import by.tomal.sw.data.net.monsterProvideApi
import io.reactivex.Single
import io.reactivex.disposables.Disposable

class GuideService(context: Context) {

    private val guideDao = AppDatabase.create(context).getGuideDao()
    private val api = monsterProvideApi()
    private var disposable: Disposable? = null

    @Throws(Exception::class)
    fun requestData(): Single<List<MonsterGuideResponse>> {
        val guideResponse = api.getGuides()

        disposable = guideResponse
            .subscribe { response ->
                run {
                    disposable?.let {
                        if (!it.isDisposed) {
                            it.dispose()
                        }
                    }
                }
                guideDao.insert(response ?: arrayListOf())
            }

        return guideResponse
    }
}