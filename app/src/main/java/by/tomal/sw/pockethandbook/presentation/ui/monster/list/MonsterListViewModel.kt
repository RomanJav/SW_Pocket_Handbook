package by.tomal.sw.pockethandbook.presentation.ui.monster.list

import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import by.tomal.sw.domain.entity.Monster
import by.tomal.sw.domain.entity.exception.AppException
import by.tomal.sw.domain.usecase.MonsterGetUseCase
import by.tomal.sw.pockethandbook.app.SWPocketHandbookApplication
import by.tomal.sw.pockethandbook.presentation.base.BaseViewModel
import by.tomal.sw.pockethandbook.presentation.ui.monster.list.MonsterState.*

class MonsterListViewModel(monsterListUseCase: MonsterGetUseCase) : BaseViewModel() {
    val monsterState = MutableLiveData<MonsterState>()
    val monsterClicked = MutableLiveData<Monster>()

    init {
        monsterState.value = Progress
        val disposable = monsterListUseCase.get().subscribe({
            monsterState.value = Done(it)
        }, {
            if(it is AppException) {
                performExceptionTypeAction(it)
            } else {

            }
            monsterState.value = Error(it)
        })
        disposableBag.add(disposable)
    }
    private fun performExceptionTypeAction(it: AppException) {
        Toast.makeText(SWPocketHandbookApplication.instance.applicationContext, "Exception occurred: $it", Toast.LENGTH_LONG).show()
    }

    fun monsterClick(monster: Monster) {
        monsterClicked.value = monster
    }
}