package by.tomal.sw.pockethandbook.presentation.ui.monster.list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import by.tomal.sw.pockethandbook.app.UseCaseProvider

class MonsterListViewModelElementFactory (private val element: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MonsterListViewModel(
            UseCaseProvider
                .provideGetMonsterUseCaseElementMonsters(element)) as T
    }
}