package by.tomal.sw.pockethandbook.presentation.ui.monster.info

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import by.tomal.sw.pockethandbook.app.UseCaseProvider

class MonsterInfoViewModelFactory(private val awakenName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MonsterInfoViewModel(UseCaseProvider.provideGetMonsterInfoUseCaseByName(awakenName)) as T
    }
}