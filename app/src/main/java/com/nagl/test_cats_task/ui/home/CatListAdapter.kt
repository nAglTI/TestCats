package com.nagl.test_cats_task.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.nagl.test_cats_task.data.model.domain.Cat
import com.nagl.test_cats_task.databinding.ItemCatBinding

class CatListAdapter(private val delegate: OnItemClickCallback): ListAdapter<Cat, CatListAdapter.CatViewHolder>(CatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemCatBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = getItem(position)
        holder.itemView.setOnClickListener {
            delegate.onItemClick(cat)
        }
        holder.bind(cat)
    }
    class CatViewHolder(private val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: Cat) {
            binding.catImageView.load(cat.url) //{
//                crossfade(durationMillis = 2000)
//                transformations(RoundedCornersTransformation(12.5f))
//            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClick(cat: Cat)
    }

    class CatDiffCallback: DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }

    }
}