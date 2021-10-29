package com.benenesyildirim.rickmorty.retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CharacterDetailFragment : Fragment() {
    lateinit var character: CharacterModel

    private lateinit var characterImage: ImageView
    private lateinit var characterName: TextView
    private lateinit var characterStatus: TextView
    private lateinit var characterSpecies: TextView
    private lateinit var characterGender: TextView
    private lateinit var characterType: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            character = requireArguments().getSerializable("character") as CharacterModel
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_character_detail, container, false)

        initView(view)
        fillView(view)
        return view
    }

    private fun fillView(view: View) {
        Glide.with(view).load(character.image).placeholder(R.drawable.temp_character_image).into(characterImage)

        characterName.text = character.name
        characterStatus.text = character.status
        characterSpecies.text = character.species
        characterGender.text = character.gender
        characterType.text = character.type
    }

    private fun initView(view: View) {
        characterImage = view.findViewById(R.id.character_detail_image_iv)
        characterName = view.findViewById(R.id.character_name_detail_tv)
        characterSpecies = view.findViewById(R.id.character_species_detail_tv)
        characterStatus = view.findViewById(R.id.character_status_detail_tv)
        characterGender = view.findViewById(R.id.character_gender_detail_tv)
        characterType = view.findViewById(R.id.character_type_detail_tv)
    }
}