package by.tomal.sw.pockethandbook.presentation.ui.monster.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import by.tomal.sw.domain.entity.Monster
import by.tomal.sw.pockethandbook.R
import by.tomal.sw.pockethandbook.app.ElementConstants.DARK
import by.tomal.sw.pockethandbook.app.ElementConstants.ELEMENT
import by.tomal.sw.pockethandbook.app.ElementConstants.EXTRA_MONSTER_NAME
import by.tomal.sw.pockethandbook.app.ElementConstants.FIRE
import by.tomal.sw.pockethandbook.app.ElementConstants.LIGHT
import by.tomal.sw.pockethandbook.app.ElementConstants.WATER
import by.tomal.sw.pockethandbook.app.ElementConstants.WIND
import by.tomal.sw.pockethandbook.presentation.base.BaseMvvmFragment
import by.tomal.sw.pockethandbook.presentation.ui.monster.info.MonsterInfoActivity
import by.tomal.sw.pockethandbook.presentation.ui.monster.list.adapter.MonsterListAdapter
import kotlinx.android.synthetic.main.fragment_list_monsters.*

class MonsterListFragment : BaseMvvmFragment<MonsterListViewModel>() {

    companion object {
        private val SHOW_MONSTER_INFO = MonsterInfoActivity::class.java
        private fun createIntent(context: Context, classActivity: Class<*>): Intent {
            return Intent(context, classActivity)
        }
    }

    override fun provideLayoutId() = R.layout.fragment_list_monsters

    override fun provideViewModel(): MonsterListViewModel {
        return ViewModelProviders.of(this, initFactory())
            .get(MonsterListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        monsterListRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        monsterListRecyclerView.adapter = MonsterListAdapter(requireContext(), viewModel)
        observeMonsterState()
        viewModel.monsterClicked
            .observe(this, Observer<Monster> { monster ->
                if (monster != null) {
                    val intent = createIntent(requireContext(), SHOW_MONSTER_INFO)
                    intent.putExtra(EXTRA_MONSTER_NAME, monster.awakenName)
                    startActivity(
                        intent
                    )
                }
            })
    }

    private fun observeMonsterState() {
        viewModel
            .monsterState
            .observe(this,
                Observer<MonsterState> { state -> if (state != null) processState(state) })
    }

    private fun processState(state: MonsterState) {
        when (state) {
            is MonsterState.Progress -> {
            }
            is MonsterState.Done -> {
                val list = state.monsterList
                (monsterListRecyclerView.adapter as MonsterListAdapter).setNewItems(list)
            }
            is MonsterState.Error -> {
                Toast.makeText(context, "Error: ${state.throwable.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun initView() {
        observeMonsterState()
    }

    private fun initFactory(): ViewModelProvider.Factory {
        lateinit var factory: ViewModelProvider.Factory
        when (arguments?.getString(ELEMENT)) {
            FIRE -> factory = MonsterListViewModelElementFactory(FIRE)
            WIND -> factory = MonsterListViewModelElementFactory(WIND)
            WATER -> factory = MonsterListViewModelElementFactory(WATER)
            LIGHT -> factory = MonsterListViewModelElementFactory(LIGHT)
            DARK -> factory = MonsterListViewModelElementFactory(DARK)
        }
        return factory
    }
}