package com.kacper.movieapp.ui.person

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kacper.movieapp.api.RetroService
import com.kacper.movieapp.model.Person
import com.kacper.movieapp.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class PersonViewModel @ViewModelInject constructor(
        private val repository: PersonRepository
) : ViewModel() {
    fun getActors(): Flow<PagingData<Person>> {
        return repository.getNowPerson().cachedIn(viewModelScope).flowOn(Dispatchers.IO)
    }

}


