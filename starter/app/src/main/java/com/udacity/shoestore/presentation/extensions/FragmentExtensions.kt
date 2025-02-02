package com.udacity.shoestore.presentation.extensions

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.handleBackPress(action: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                action()
            }
        }
    )
}
