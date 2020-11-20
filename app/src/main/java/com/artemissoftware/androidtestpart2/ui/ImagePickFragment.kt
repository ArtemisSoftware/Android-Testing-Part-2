package com.artemissoftware.androidtestpart2.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.artemissoftware.androidtestpart2.R
import com.artemissoftware.androidtestpart2.adapters.ImageAdapter
import com.artemissoftware.androidtestpart2.util.constants.AppConstants
import kotlinx.android.synthetic.main.fragment_image_pick.*
import javax.inject.Inject

class ImagePickFragment @Inject constructor(val imageAdapter: ImageAdapter) : Fragment(R.layout.fragment_image_pick) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        setupRecyclerView()

        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setCurImageUrl(it)
        }
    }

    private fun setupRecyclerView() {
        rvImages.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), AppConstants.GRID_SPAN_COUNT)
        }
    }
}
