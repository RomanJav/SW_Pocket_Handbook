package by.tomal.sw.pockethandbook.presentation.ui.monster.guide

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import by.tomal.sw.pockethandbook.app.UseCaseProvider

class MonsterGuideViewModelFactory (private val awakenName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MonsterGuideViewModel(UseCaseProvider.provideGetMonsterGuideUseCaseByName(awakenName)) as T
    }
}