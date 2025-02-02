package com.udacity.shoestore.presentation.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.presentation.shoelist.ShoeListViewModel

class ShoeDetailFragment : Fragment() {

    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!

    private val shoeListViewModel: ShoeListViewModel by activityViewModels()
    private val shoeDetailViewModel: ShoeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeDetailBinding.inflate(inflater, container, false)
        binding.viewModel = shoeDetailViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupListener()
    }

    private fun setupListener() = binding.run {
        saveButton.setOnClickListener {
            shoeDetailViewModel.addShoe()
        }

        cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObserver() {
        shoeDetailViewModel.shoeAdded.observe(viewLifecycleOwner) { isAdded ->
            if (isAdded) {
                val newShoe = Shoe(
                    name = shoeDetailViewModel.shoeName.value ?: String(),
                    size = shoeDetailViewModel.shoeSize.value?.toDoubleOrNull() ?: 0.0,
                    company = shoeDetailViewModel.shoeCompany.value ?: String(),
                    description = shoeDetailViewModel.shoeDescription.value ?: String(),
                    images = emptyList()
                )

                shoeListViewModel.addShoe(newShoe)
                shoeDetailViewModel.resetShoeAdded()
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
