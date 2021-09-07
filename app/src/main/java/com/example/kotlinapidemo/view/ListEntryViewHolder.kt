package com.example.kotlinapidemo.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapidemo.R
import com.example.kotlinapidemo.data.model.PokemonListEntryResult

class ListEntryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var url: String? = null
    private var nameTextView: TextView? = null

    fun bindView(item: PokemonListEntryResult) {
        nameTextView = view.findViewById(R.id.pokemon_name)
        val name = item.name
        nameTextView?.text = name
        url = item.url

        view.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                view.context,
                "Click on name: $name",
                Toast.LENGTH_LONG
            ).show()

            // call PokemonActivity method and pass name as a parameter
//            if (name != null) {
//                (view.context as MainActivity).openDetailFragment(name)
//            }

            // or you can implement Parcelable in the data class and pass the name as part of a bundle
            val fragment: Fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable("pokemon", item)
            fragment.arguments = bundle
            val fm: FragmentManager = (it.context as MainActivity).supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.replace(android.R.id.content, fragment)
            ft.addToBackStack("detailFragment").commit()
        })
    }
}