package com.kacper.movieapp.ui.person

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.kacper.movieapp.R
import com.kacper.movieapp.databinding.FragmentPersonBinding
import com.kacper.movieapp.model.Person
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_person.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonFragment : Fragment(R.layout.fragment_person) {

    private val viewModel by viewModels<PersonViewModel>()
    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPersonBinding.bind(view)

        val adapter = PersonAdapter(object : PersonAdapter.ActorListener {

            override fun onClickActor(result: Person?) {
                findNavController().navigate(R.id.action_nav_actor_to_fragment2Details,
                        bundleOf("person_data" to result)
                )
            }
        })
        binding.apply {
            rv_actor.setHasFixedSize(true)
            rv_actor.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = PersonLoadStateAdapter { adapter.retry() },
                    footer = PersonLoadStateAdapter { adapter.retry() }
            )
            btn_try_again_person.setOnClickListener {
                adapter.retry()
            }
        }
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progress_bar_person.isVisible = loadState.source.refresh is LoadState.Loading
                rv_actor.isVisible = loadState.source.refresh is LoadState.NotLoading
                btn_try_again_person.isVisible = loadState.source.refresh is LoadState.Error
                tv_failed_person.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading && loadState
                                .append.endOfPaginationReached && adapter.itemCount < 1) {
                    rv_actor.isVisible = false
                    tv_not_found_person.isVisible = true

                } else {
                    tv_not_found_person.isVisible = false
                }
            }
        }
        lifecycleScope.launch {
            viewModel.getActors().collect {
                adapter.submitData(it)
            }
        }
        setHasOptionsMenu(true)
    }


}

