package by.tomal.sw.pockethandbook.presentation.ui.monster.info

import by.tomal.sw.domain.entity.MonsterInfo

sealed class MonsterInfoState {
    object Progress : MonsterInfoState()
    class Done(val monsterInfo: MonsterInfo) : MonsterInfoState()
    class Error(val throwable: Throwable) : MonsterInfoState()
}