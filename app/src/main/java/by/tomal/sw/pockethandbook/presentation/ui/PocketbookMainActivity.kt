package by.tomal.sw.pockethandbook.presentation.ui


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import by.tomal.sw.pockethandbook.R
import by.tomal.sw.pockethandbook.app.ElementConstants.DARK
import by.tomal.sw.pockethandbook.app.ElementConstants.ELEMENT
import by.tomal.sw.pockethandbook.app.ElementConstants.FIRE
import by.tomal.sw.pockethandbook.app.ElementConstants.LIGHT
import by.tomal.sw.pockethandbook.app.ElementConstants.SPECIAL
import by.tomal.sw.pockethandbook.app.ElementConstants.WATER
import by.tomal.sw.pockethandbook.app.ElementConstants.WIND
import by.tomal.sw.pockethandbook.presentation.base.BaseMvvmActivity
import by.tomal.sw.pockethandbook.presentation.ui.monster.list.MonsterListFragment
import by.tomal.sw.pockethandbook.presentation.ui.monster.list.MonsterListViewModel
import by.tomal.sw.pockethandbook.presentation.ui.monster.list.MonsterListViewModelFactory
import kotlinx.android.synthetic.main.activity_monster_list.*

class PocketbookMainActivity : BaseMvvmActivity<MonsterListViewModel>() {

    companion object {
        fun createFragment(element: String): MonsterListFragment {
            val fragment = MonsterListFragment()
            fragment.arguments = Bundle().apply { putString(ELEMENT, element) }
            return fragment
        }
    }

    override fun provideViewModel(): MonsterListViewModel {
        return ViewModelProviders.of(this, MonsterListViewModelFactory())
            .get(MonsterListViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_monster_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectElementFragment(createFragment(FIRE))
        select_element.setOnCheckedChangeListener { _, checkedId ->
            if (!MonsterListFragment().isInLayout) {
                selectElement(checkedId)
            }
        }
    }

    private fun selectElement(checkedId: Int) {
        when (checkedId) {
            R.id.select_fire_monsters -> {
                selectElementFragment(createFragment(FIRE))
            }
            R.id.select_wind_monsters -> {
                selectElementFragment(createFragment(WIND))
            }
            R.id.select_water_monsters -> {
                selectElementFragment(createFragment(WATER))
            }
            R.id.select_light_monsters -> {
                selectElementFragment(createFragment(LIGHT))
            }
            R.id.select_dark_monsters -> {
                selectElementFragment(createFragment(DARK))
            }
            R.id.select_special_monsters -> {
                selectElementFragment(createFragment(SPECIAL))
            }
        }
    }

    private fun selectElementFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.monster_list, fragment)
            .commit()
    }
}