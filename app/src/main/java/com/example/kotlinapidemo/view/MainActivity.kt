package com.example.kotlinapidemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinapidemo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, MainFragment.newInstance())
                .addToBackStack("mainFragment").commit()
        }
    }

    /* pass the name to a new instance of DetailFragment, where we will do a new
      network request to get information about that character for the detail page*/
    fun openDetailFragment(name: String) {
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, DetailFragment.newInstance(name))
            .addToBackStack(null).commit()
    }
}