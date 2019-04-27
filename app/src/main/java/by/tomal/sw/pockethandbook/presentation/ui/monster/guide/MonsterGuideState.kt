package by.tomal.sw.pockethandbook.presentation.ui.monster.guide

import by.tomal.sw.domain.entity.MonsterGuide

sealed class MonsterGuideState {
    object Progress : MonsterGuideState()
    class Done(val monsterGuide: MonsterGuide) : MonsterGuideState()
    class Error(val throwable: Throwable) : MonsterGuideState()
}