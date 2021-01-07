package com.kacper.movieapp.ui.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kacper.movieapp.R
import com.kacper.movieapp.databinding.ItemPersonBinding
import com.kacper.movieapp.model.Person

class PersonAdapter(private val listener: ActorListener) : PagingDataAdapter<Person, PersonAdapter.PersonViewHolder>(DiffUtilCallBack) {

    inner class PersonViewHolder(private val binding: ItemPersonBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Person, listener: ActorListener?){
            with(binding){
                Glide.with(binding.root.context)
                    .load("${result.baseUrl}${result.profile_path}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivActorImage)

                txtNameActor.text = result.name
                root.setOnClickListener {  listener?.onClickActor(result)}
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }
    companion object {
        private val DiffUtilCallBack = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem == newItem
        }
    }

    interface ActorListener {
        fun onClickActor(result: Person?)
    }

}
