package com.example.practicemvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.practicemvvm.R
import com.example.practicemvvm.databinding.FragmentProductDetailBinding


class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var binding: FragmentProductDetailBinding
    private val args by navArgs<ProductDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)

        Glide.with(
            requireContext()
        ).load(
            args.thumbnail
        ).into(binding.ivDetail)

        binding.detailName.text = args.title
        binding.tvDescription.text = args.description
        binding.tvPrice.text = args.price.toString()

        binding.btnCLose.setOnClickListener {
            findNavController().navigate(ProductDetailFragmentDirections.actionProductDetailFragmentToProductListFragment())
        }

    }
}