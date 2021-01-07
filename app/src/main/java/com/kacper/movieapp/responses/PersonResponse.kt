package com.kacper.movieapp.responses

import com.kacper.movieapp.model.Person

data class PersonResponse(
        val results: List<Person>
)