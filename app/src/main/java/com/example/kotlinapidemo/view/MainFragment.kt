package com.example.kotlinapidemo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapidemo.R
import com.example.kotlinapidemo.data.ApiClient
import com.example.kotlinapidemo.data.PokemonApi
import com.example.kotlinapidemo.data.model.PokemonListEntryResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainFragment : Fragment() {

    //TODO fix memory leak - done, read comments
    private var disposable: CompositeDisposable? = null
    private val api: PokemonApi? = ApiClient.api()
    private val adapter: NameListAdapter = NameListAdapter()
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable = CompositeDisposable()
        //  PokemonFragment.context = context   // get rid of this, not needed
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view.findViewById(R.id.pokemon_list)
        recyclerView?.layoutManager = object : LinearLayoutManager(requireContext()) {}
        recyclerView?.adapter = adapter

        return view
    }

    override fun onStart() {
        super.onStart()
        api?.let { api ->
            val call: Disposable = api.pokemonList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        populateList(it?.response()?.body()?.results ?: emptyList())
                    },
                    {
                        Log.e("Api error", it?.message ?: "error")
                    }
                )

            disposable?.add(call)
        }
    }

    private fun populateList(results: List<PokemonListEntryResult>) {
        adapter.list = results
        adapter.notifyDataSetChanged()
    }

    companion object {
//        Storing context in a static variable (or any variable) is not good practice and can lead to memory leaks
//        When we use Fragment in our app, we often time need access to Context or Activity.
//        We do it by calling methods such as getContext() and getActivity() methods. But, in kotlin, these methods return
//        nullables and can crash due to a null pointer if Fragment isn't attached to any Activity. ("activity!!" this is a nullable, or "activity as Activity")
//        You can use requireActivity() or requireContext() which will make sure that Fragment is attached and returns a non null activity (java is "getActivity()")

        /*In Java/Android a static variable or constant will not be garbage collected. It just stays there once the class that holds it is loaded via a class loader.
        The class loader is afaik always the same for all classes inside your app and its the one that has static references to all your classes
        (to e.g. MyInnerClass.class). Since the class loader does not go away your classes won't do that either since they are referenced & therefore not garbage collectable.*/
        /*Even if no reference to SomeClass exists (e.g. the Activity that showed your custom SurfaceView has ended) the static reference to the Context (and any other static
        variable / constant in SomeClass will remain. You can consider all of them leaked since it is not possible to garbage collect that Context etc. If you have a regular
        variable reference something then once the instance that contains that variable has no more references to it the whole instance including its references to other things
        can and will be garbage collected.*/

        // ? means the variable might be null, while !! means it will definitely not be null

        //       private val context: Context? = null   // get rid of this, not needed
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }
}