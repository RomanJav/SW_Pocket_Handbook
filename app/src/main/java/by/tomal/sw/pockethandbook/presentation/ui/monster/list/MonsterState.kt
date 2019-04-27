package by.tomal.sw.pockethandbook.presentation.ui.monster.list

import by.tomal.sw.domain.entity.Monster

sealed class MonsterState {
    object Progress : MonsterState()
    class Done(val monsterList: List<Monster>) : MonsterState()
    class Error(val throwable: Throwable) : MonsterState()
}