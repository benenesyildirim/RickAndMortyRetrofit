package com.benenesyildirim.rickmorty.retrofit

import java.io.Serializable

data class CharacterModel(
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val type: String?,
    val image: String?) : Serializable

data class Response(val results: List<CharacterModel>)