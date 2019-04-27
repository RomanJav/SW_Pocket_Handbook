package by.tomal.sw.pockethandbook.presentation.ui.monster.list.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.tomal.sw.domain.entity.Monster
import by.tomal.sw.pockethandbook.R
import by.tomal.sw.pockethandbook.presentation.ui.monster.list.MonsterListViewModel

class MonsterListAdapter(private val context: Context, private val viewModel: MonsterListViewModel): RecyclerView.Adapter<MonsterListItemHolder>() {

    private var monsters: List<Monster> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterListItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.monster_list_item, parent, false)
        return MonsterListItemHolder(view)
    }

    override fun getItemCount(): Int {
        return monsters.size
    }

    override fun onBindViewHolder(holder: MonsterListItemHolder, position: Int) {
        holder.bind(monsters[position], viewModel)
    }

    fun setNewItems(items: List<Monster>) {
        monsters = items
        notifyDataSetChanged()
    }
}