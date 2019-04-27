package by.tomal.sw.pockethandbook.presentation.ui.monster.guide

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import by.tomal.sw.pockethandbook.R
import by.tomal.sw.pockethandbook.app.ElementConstants.EXTRA_MONSTER_NAME
import by.tomal.sw.pockethandbook.presentation.base.BaseMvvmActivity
import kotlinx.android.synthetic.main.activity_monster_guide.*

class MonsterGuideActivity : BaseMvvmActivity<MonsterGuideViewModel>() {
    override fun provideViewModel(): MonsterGuideViewModel {
        return ViewModelProviders.of(this, MonsterGuideViewModelFactory(intent.getStringExtra(EXTRA_MONSTER_NAME)))
            .get(MonsterGuideViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_monster_guide
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
            .monsterGuideState
            .observe(this, Observer<MonsterGuideState> { state ->
                if (state != null) processState(state)
            })
    }

    private fun processState(state: MonsterGuideState) {
        when (state) {
            is MonsterGuideState.Progress -> {
            }
            is MonsterGuideState.Done -> {
                monster_rune_recommendations.text = state.monsterGuide.runeRecommendations
                monster_good_for.text = state.monsterGuide.goodFor
                monster_review.text = state.monsterGuide.review
                monster_positives.text = state.monsterGuide.positives
                monster_negatives.text = state.monsterGuide.negatives
            }
            is MonsterGuideState.Error -> {
                Toast.makeText(this, "Error: ${state.throwable.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }
}