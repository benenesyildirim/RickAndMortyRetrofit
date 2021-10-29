package com.benenesyildirim.rickmorty.retrofit

import android.R.attr.category
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class CharacterListAdapter(private val characterList: List<CharacterModel>, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {
    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val characterImage: ImageView = view.findViewById(R.id.character_list_image_iv)
        private val characterName: TextView = view.findViewById(R.id.character_list_name_tv)
        private val characterSpecies: TextView = view.findViewById(R.id.character_list_species_tv)

        fun bindItems(item: CharacterModel) {
            fillView(item)
        }

        private fun fillView(item: CharacterModel) {
            Glide.with(itemView).load(item.image).placeholder(R.drawable.temp_character_image).into(characterImage)
            characterName.text = item.name
            characterSpecies.text = item.species
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_row_design, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindItems(characterList[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClicked(holder, characterList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}