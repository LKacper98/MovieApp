package com.kacper.movieapp.paging

import androidx.paging.PagingSource
import com.kacper.movieapp.api.RetroService
import com.kacper.movieapp.model.Person
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PersonPagingSource(
        private val retroService: RetroService
) : PagingSource<Int, Person>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = retroService.getNowPerson(position)

            val persons = response.results
            LoadResult.Page(
                    data = persons,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (persons.isEmpty()) null else position + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }
}