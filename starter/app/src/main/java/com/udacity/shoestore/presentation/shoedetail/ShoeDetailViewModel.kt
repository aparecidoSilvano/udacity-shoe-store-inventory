package com.udacity.shoestore.presentation.shoedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeDetailViewModel : ViewModel() {

    val shoeName = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()

    private val _shoeAdded = MutableLiveData<Boolean>()
    val shoeAdded: LiveData<Boolean> get() = _shoeAdded

    fun addShoe() {
        if (!shoeName.value.isNullOrEmpty() &&
            !shoeSize.value.isNullOrEmpty() &&
            !shoeCompany.value.isNullOrEmpty() &&
            !shoeDescription.value.isNullOrEmpty()
        ) {
            _shoeAdded.value = true
        }
    }

    fun resetShoeAdded() {
        _shoeAdded.value = false
    }
}
