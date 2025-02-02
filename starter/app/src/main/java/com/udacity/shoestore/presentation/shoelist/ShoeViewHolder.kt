package com.udacity.shoestore.presentation.shoelist

import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe

class ShoeViewHolder(private val binding: ItemShoeBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(shoe: Shoe) {
        binding.shoeName.text = shoe.name
        binding.shoeCompany.text = shoe.company
        binding.shoeSize.text = binding.root.context.getString(R.string.shoe_list_item_size, shoe.size.toInt())
        binding.shoeDescription.text = shoe.description
    }
}
