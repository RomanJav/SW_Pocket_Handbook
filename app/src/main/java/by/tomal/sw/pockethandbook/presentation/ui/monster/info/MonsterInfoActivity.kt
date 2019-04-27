package by.tomal.sw.pockethandbook.presentation.ui.monster.info

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.tomal.sw.pockethandbook.R
import by.tomal.sw.pockethandbook.app.ElementConstants.EXTRA_MONSTER_NAME
import by.tomal.sw.pockethandbook.presentation.base.BaseMvvmActivity
import by.tomal.sw.pockethandbook.presentation.ui.monster.guide.MonsterGuideActivity
import by.tomal.sw.pockethandbook.presentation.ui.monster.list.MonsterListFragment
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import jp.wasabeef.picasso.transformations.CropTransformation
import kotlinx.android.synthetic.main.activity_monster_info.*

class MonsterInfoActivity : BaseMvvmActivity<MonsterInfoViewModel>() {
    companion object {
        private val SHOW_MONSTER_GUIDE = MonsterGuideActivity::class.java
        private fun createIntent(context: Context, classActivity: Class<*>): Intent {
            return Intent(context, classActivity)
        }
    }
    override fun provideViewModel(): MonsterInfoViewModel {
        return ViewModelProviders.of(this, MonsterInfoViewModelFactory(intent.getStringExtra(EXTRA_MONSTER_NAME)))
            .get(MonsterInfoViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_monster_info
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
            .monsterInfoState
            .observe(this, Observer<MonsterInfoState> { state ->
                if (state != null) processState(state)
            })
    }

    private fun processState(state: MonsterInfoState) {
        when (state) {
            is MonsterInfoState.Progress -> {
            }
            is MonsterInfoState.Done -> {
                Picasso.get().load(state.monsterInfo.icon).resize(300, 300).into(monster_icon)
                monster_name.text = "${state.monsterInfo.awakenName} (${state.monsterInfo.name})"
                monster_grade.text = state.monsterInfo.grade
                monster_type.text = state.monsterInfo.type
                Picasso.get().load(state.monsterInfo.icon)
                    .resize(monster_head_info.width*3, monster_head_info.width*3)
                    .transform(CropTransformation(monster_head_info.width*3, 900))
                    .transform(BlurTransformation(this, 25, 3))
                    .into(blurBackgroundIcon)
                monster_awake_bonus.text = state.monsterInfo.awakeBonus
                monster_skill_first.text = state.monsterInfo.skillFirst
                monster_skill_second.text = state.monsterInfo.skillSecond
                monster_skill_third.text = state.monsterInfo.skillThird
                monster_skill_forth_or_lead.text = state.monsterInfo.skillForth
                monster_guide.setOnClickListener {
                    val intent = createIntent(this, SHOW_MONSTER_GUIDE)
                    intent.putExtra(EXTRA_MONSTER_NAME, state.monsterInfo.awakenName)
                    startActivity(
                        intent
                    )
                }
            }
            is MonsterInfoState.Error -> {
                Toast.makeText(this, "Error: ${state.throwable.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }
}