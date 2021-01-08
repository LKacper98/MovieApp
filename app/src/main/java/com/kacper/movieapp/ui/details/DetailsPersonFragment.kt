package com.kacper.movieapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kacper.movieapp.R
import com.kacper.movieapp.databinding.FragmentPersonDetailsBinding
import com.kacper.movieapp.model.KnownFor
import com.kacper.movieapp.model.Person
import kotlinx.android.synthetic.main.fragment_person_details.*

class DetailsPersonFragment : Fragment(R.layout.fragment_person_details) {

    private var _binding: FragmentPersonDetailsBinding? = null

    private val binding get() = _binding

    private var person: Person? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        person = arguments?.getSerializable("person_data") as Person?
        _binding = FragmentPersonDetailsBinding.bind(view)

        val firstOverview = person?.known_for?.getOrNull(0)
        val firstConent = "${firstOverview?.title}\n${firstOverview?.overview}"

        val secondOverview = person?.known_for?.getOrNull(1)
        val secondContent = "${secondOverview?.title}\n${secondOverview?.overview}"



        binding?.original?.text = "$firstConent\n\n$secondContent"

        name_actor.text = person?.name

        Glide.with(requireContext())
                .load("${person?.baseUrl}${person?.profile_path}")
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(image_profile)
    }


}