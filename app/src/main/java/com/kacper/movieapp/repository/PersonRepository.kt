package com.kacper.movieapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.kacper.movieapp.api.RetroService
import com.kacper.movieapp.paging.PersonPagingSource
import javax.inject.Inject

class PersonRepository @Inject constructor(private val retroService: RetroService) {

    fun getNowPerson() =
            Pager(config = PagingConfig(
                    pageSize = 5,
                    maxSize = 20,
                    enablePlaceholders = false),
                    pagingSourceFactory = { PersonPagingSource(retroService) }
            ).flow
}