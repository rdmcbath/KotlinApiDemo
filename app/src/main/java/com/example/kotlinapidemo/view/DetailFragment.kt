package com.example.kotlinapidemo.view

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlinapidemo.R
import com.example.kotlinapidemo.data.ApiClient
import com.example.kotlinapidemo.data.PokemonApi
import com.example.kotlinapidemo.data.model.PokemonDetail
import com.example.kotlinapidemo.data.model.PokemonListEntryResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailFragment : Fragment() {
    private val api: PokemonApi? = ApiClient.api()
    private var disposable: CompositeDisposable? = null
    private var textViewName: TextView? = null
    private var textViewHeight: TextView? = null
    private var textViewWeight: TextView? = null
    private var clickedName: String = ""
    private lateinit var item: PokemonListEntryResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments
        item = bundle?.getParcelable<Parcelable>("pokemon") as PokemonListEntryResult
        clickedName = item.name.toString()

        disposable = CompositeDisposable()
//        clickedName = arguments?.getString(ARG_NAME).toString()  // if we wanted to just pass the name string without implementing Parcelable
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        textViewName = view.findViewById(R.id.pokemon_name)
        textViewHeight = view.findViewById(R.id.pokemon_height)
        textViewWeight = view.findViewById(R.id.pokemon_weight)
        return view
    }

    override fun onStart() {
        super.onStart()
        api?.let { api ->
            val call: Disposable = api.getDetailByName(clickedName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        populateDetails(it.response()?.body())
                    },
                    {
                        Log.e("Api error", it?.message ?: "error")
                    }
                )

            disposable?.add(call)
        }
    }

    private fun populateDetails(model: PokemonDetail?) {
        textViewHeight?.text = "${getString(R.string.height)}: ${model?.height.toString()}"
        textViewWeight?.text = "${getString(R.string.weight)}: ${model?.weight.toString()}"
        textViewName?.text = "${getString(R.string.name)}: ${model?.name}"
    }

    // if we wanted to just open a new instance of this fragment with the name param
    companion object {
        private const val ARG_NAME = "name"

        fun newInstance(name: String): DetailFragment {
            val fragment = DetailFragment()

            val bundle = Bundle().apply {
                putString(ARG_NAME, name)
            }

            fragment.arguments = bundle

            return fragment
        }
    }
}