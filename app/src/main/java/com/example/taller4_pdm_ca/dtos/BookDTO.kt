package com.example.taller4_pdm_ca.dtos

import com.example.taller4_pdm_ca.pojos.Author
import com.example.taller4_pdm_ca.pojos.Publisher
import com.example.taller4_pdm_ca.pojos.Tags

data class BookDTO(
    val id: Int,
    val cover_url: String,
    val title: String,
    val edition: String,
    val synopsis: String,
    val isbn: String,
    val fav: Boolean,
    val publisher: Publisher,
    val authors: List<Author>,
    val tags: List<Tags>
) {
}