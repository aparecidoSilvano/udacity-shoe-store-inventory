package com.udacity.shoestore.presentation.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.presentation.extensions.handleBackPress

class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var adapter: ShoeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupShoeList()
        setupListener()
        setupMenu()
    }

    private fun setupShoeList() {
        adapter = ShoeListAdapter()
        binding.shoeRecyclerView.adapter = adapter

        viewModel.shoeList.observe(viewLifecycleOwner) { shoes ->
            adapter.submitList(shoes)
        }
    }

    private fun setupListener() {
        binding.addShoeFab.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        handleBackPress { requireActivity().finish() }
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shoe_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.logout -> {
                        findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
