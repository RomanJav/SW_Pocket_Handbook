package by.tomal.sw.pockethandbook.presentation.ui.monster.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import by.tomal.sw.domain.entity.Monster
import by.tomal.sw.pockethandbook.presentation.ui.monster.list.MonsterListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.monster_list_item.view.*

class MonsterListItemHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(monster: Monster, viewModel: MonsterListViewModel) {
        Picasso.get().load(monster.icon).into(itemView.monsterImage)
        itemView.monsterName.text = monster.name
        itemView.monsterCard.setOnClickListener {
            viewModel.monsterClick(monster)
        }
    }
}