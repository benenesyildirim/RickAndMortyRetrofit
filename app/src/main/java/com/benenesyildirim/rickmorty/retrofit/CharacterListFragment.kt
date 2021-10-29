package com.benenesyildirim.rickmorty.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListFragment : Fragment() {
    private lateinit var characterList: RecyclerView
    private lateinit var characterListAdapter: CharacterListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_character_list, container, false)
        characterList = view.findViewById(R.id.character_list_rv)

        callCharacters(view)
        return view
    }

    private fun callCharacters(view: View) {
        val apiInterface = ApiInterface.create().getCharacters()

        apiInterface.enqueue(object : Callback<com.benenesyildirim.rickmorty.retrofit.Response> {
            override fun onResponse(call: Call<com.benenesyildirim.rickmorty.retrofit.Response>, response: Response<com.benenesyildirim.rickmorty.retrofit.Response>) {
                if (response.body() != null) characterListAdapter = CharacterListAdapter(response.body()!!.results, itemClickListener = object : ItemClickListener {
                    override fun onItemClicked(holder: CharacterListAdapter.CharacterViewHolder, item: Any, position: Int) {
                        navigateToDetail(response, position, view)
                    }
                })
                characterList.adapter = characterListAdapter
            }

            override fun onFailure(call: Call<com.benenesyildirim.rickmorty.retrofit.Response>, t: Throwable) {
                Toast.makeText(context, "Please check your connection.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToDetail(response: Response<com.benenesyildirim.rickmorty.retrofit.Response>, position: Int, view: View) {
        val bundle = Bundle()
        bundle.putSerializable("character", response.body()!!.results[position])
        Navigation.findNavController(view).navigate(R.id.action_characterListFragment_to_characterDetailFragment, bundle)
    }
}

interface ItemClickListener {
    fun onItemClicked(holder: CharacterListAdapter.CharacterViewHolder, item: Any, position: Int)
}