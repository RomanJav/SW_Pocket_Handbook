package by.tomal.sw.pockethandbook.presentation.ui.monster.guide

import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import by.tomal.sw.domain.entity.exception.AppException
import by.tomal.sw.domain.usecase.MonsterGetGuideUseCase
import by.tomal.sw.pockethandbook.app.SWPocketHandbookApplication
import by.tomal.sw.pockethandbook.presentation.base.BaseViewModel
import by.tomal.sw.pockethandbook.presentation.ui.monster.guide.MonsterGuideState.*

class MonsterGuideViewModel (monsterGuideUseCase: MonsterGetGuideUseCase) : BaseViewModel() {
    val monsterGuideState = MutableLiveData<MonsterGuideState>()

    init {
        monsterGuideState.value = Progress
        val disposable = monsterGuideUseCase.getMonsterGuide().subscribe({
            monsterGuideState.value = Done(it)
        }, {
            if(it is AppException) {
                performExceptionTypeAction(it)
            } else {

            }
            monsterGuideState.value = Error(it)
        })
        disposableBag.add(disposable)
    }
    private fun performExceptionTypeAction(it: AppException) {
        Toast.makeText(SWPocketHandbookApplication.instance.applicationContext, "Exception occurred: $it", Toast.LENGTH_LONG).show()
    }
}