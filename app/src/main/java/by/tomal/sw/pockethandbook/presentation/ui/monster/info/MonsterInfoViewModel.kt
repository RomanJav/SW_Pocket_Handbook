package by.tomal.sw.pockethandbook.presentation.ui.monster.info

import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import by.tomal.sw.domain.entity.exception.AppException
import by.tomal.sw.domain.usecase.MonsterGetInfoUseCase
import by.tomal.sw.pockethandbook.app.SWPocketHandbookApplication
import by.tomal.sw.pockethandbook.presentation.base.BaseViewModel
import by.tomal.sw.pockethandbook.presentation.ui.monster.info.MonsterInfoState.*

class MonsterInfoViewModel(monsterInfoUseCase: MonsterGetInfoUseCase) : BaseViewModel() {
    val monsterInfoState = MutableLiveData<MonsterInfoState>()

    init {
        monsterInfoState.value = Progress
        val disposable = monsterInfoUseCase.getMonsterInfo().subscribe({
            monsterInfoState.value = Done(it)
        }, {
            if(it is AppException) {
                performExceptionTypeAction(it)
            } else {

            }
            monsterInfoState.value = Error(it)
        })
        disposableBag.add(disposable)
    }
    private fun performExceptionTypeAction(it: AppException) {
        Toast.makeText(SWPocketHandbookApplication.instance.applicationContext, "Exception occurred: $it", Toast.LENGTH_LONG).show()
    }
}