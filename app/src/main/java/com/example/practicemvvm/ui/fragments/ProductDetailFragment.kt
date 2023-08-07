package com.example.practicemvvm.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.practicemvvm.R
import com.example.practicemvvm.databinding.FragmentProductDetailBinding
import com.example.practicemvvm.ui.AdsBanner
import com.example.practicemvvm.ui.AdsInterstitial
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {
    private lateinit var binding: FragmentProductDetailBinding
    private val args by navArgs<ProductDetailFragmentArgs>()
    private var bannerDetail = AdsBanner()
    private var interstitialAd = AdsInterstitial()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)
        val appContext = context!!.applicationContext

        //Ads Functions
        bannerDetail.adBanner(appContext, binding)
        interstitialAd.adInterstitial(appContext)

        //SafeArgs
        Glide.with(requireContext()).load(args.thumbnail).into(binding.ivDetail)

        binding.detailName.text = args.title
        binding.tvDescription.text = args.description
        binding.tvPrice.text = args.price.toString()

        //Click Button Back to list
        binding.btnCLose.setOnClickListener {
            findNavController().navigate(ProductDetailFragmentDirections.actionProductDetailFragmentToProductListFragment())
        }

        //Click button show Ad INTERSTITIAL
        binding.btnAds.setOnClickListener {
            interstitialAd.showAdInterstitial(requireContext())
        }
    }
}