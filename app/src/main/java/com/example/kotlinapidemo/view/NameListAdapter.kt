package com.example.kotlinapidemo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapidemo.R
import com.example.kotlinapidemo.data.model.PokemonListEntryResult
import java.util.ArrayList

class NameListAdapter : RecyclerView.Adapter<ListEntryViewHolder?>() {
    var list: List<PokemonListEntryResult> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListEntryViewHolder {
        return ListEntryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListEntryViewHolder, position: Int) {
        val itemPosition = list[position]

        holder.bindView(itemPosition)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}