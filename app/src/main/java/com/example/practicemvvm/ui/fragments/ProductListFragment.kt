package com.example.practicemvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practicemvvm.R
import com.example.practicemvvm.data.dataSource.ProductsDataSource
import com.example.practicemvvm.data.model.Products
import com.example.practicemvvm.data.repository.ProductsRepositoryImp
import com.example.practicemvvm.data.service.RetrofitClient
import com.example.practicemvvm.databinding.FragmentProductListBinding
import com.example.practicemvvm.ui.adapter.ProductsAdapter
import com.example.practicemvvm.utils.Resource
import com.example.practicemvvm.viewModel.ProductViewModelFactory
import com.example.practicemvvm.viewModel.ProductsViewModel

class ProductListFragment : Fragment(R.layout.fragment_product_list), ProductsAdapter.OnProductClickListener {

    private lateinit var binding: FragmentProductListBinding

    private val viewModel by viewModels<ProductsViewModel>{
        ProductViewModelFactory(ProductsRepositoryImp(
            ProductsDataSource(RetrofitClient.webservice))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductListBinding.bind(view)

        viewModel.getProducts().observe(viewLifecycleOwner){ list ->
            when (list) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvList.adapter = ProductsAdapter(list.data.products, this@ProductListFragment)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onProductClick(product: Products) {
        val action = ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
            product.title,
            product.description,
            product.price,
            product.thumbnail,
            product.id
        )
        findNavController().navigate(action)
    }
}