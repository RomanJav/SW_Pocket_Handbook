package by.tomal.sw.pockethandbook.presentation.ui.monster.list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import by.tomal.sw.pockethandbook.app.UseCaseProvider

class MonsterListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MonsterListViewModel(
            UseCaseProvider
            .provideGetMonsterUseCase()) as T
    }
}