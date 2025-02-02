package com.udacity.shoestore.presentation.shoelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe

class ShoeListAdapter : ListAdapter<Shoe, ShoeViewHolder>(ShoeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ItemShoeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        val shoe = getItem(position)
        holder.bind(shoe)
    }
}

class ShoeDiffCallback : DiffUtil.ItemCallback<Shoe>() {
    override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean = oldItem.name == newItem.name && oldItem.company == newItem.company
    override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean = oldItem == newItem
}
