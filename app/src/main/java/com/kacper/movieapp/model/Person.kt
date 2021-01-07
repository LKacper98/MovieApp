package com.kacper.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
data class Person(
        val id: String,
        val known_for_department: String,
        val known_for: List<KnownFor>,
        val name: String,
        val profile_path: String,
        val popularity: String
) : Serializable, Parcelable {
    val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}