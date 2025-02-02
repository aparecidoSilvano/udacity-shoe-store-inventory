package com.udacity.shoestore.presentation.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<List<Shoe>>(
        mutableListOf(
            Shoe("Shoe 1", 10.0, "Company 1", "Description 1"),
            Shoe("Shoe 2", 11.0, "Company 2", "Description 2"),
            Shoe("Shoe 3", 12.0, "Company 3", "Description 3"),
            Shoe("Shoe 4", 13.0, "Company 4", "Description 4"),
            Shoe("Shoe 5", 14.0, "Company 5", "Description 5"),
            Shoe("Shoe 6", 15.0, "Company 6", "Description 6"),
        )
    )
    val shoeList: LiveData<List<Shoe>> get() = _shoeList

    fun addShoe(shoe: Shoe) {
        val updatedList = _shoeList.value?.toMutableList() ?: mutableListOf()
        updatedList.add(shoe)
        _shoeList.value = updatedList
    }
}