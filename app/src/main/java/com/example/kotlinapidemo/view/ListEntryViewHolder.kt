package com.example.kotlinapidemo.view

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapidemo.R
import com.example.kotlinapidemo.data.model.PokemonListEntryResult

class ListEntryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var url: String? = null
    private var nameTextView: TextView? = null

    fun bindView(item: PokemonListEntryResult) {
        nameTextView = view.findViewById(R.id.pokemon_name)
        nameTextView?.text = item.name
        url = item.url

        val name = item.name

        view.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                view.context,
                "Click on name: $name",
                Toast.LENGTH_LONG
            ).show()

            // call PokemonActivity method and pass name as a parameter
            (view.context as MainActivity).openDetailFragment(name)
        })
    }
}