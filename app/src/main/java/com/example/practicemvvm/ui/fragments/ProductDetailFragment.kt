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
    private var interstitialAd: InterstitialAd? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)
        val appContext = context!!.applicationContext

        //Ads Functions
        adBanner(appContext)
        adInterstitial(appContext)

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
            showAd()
        }
    }

    //Ad INTERSTITIAL
    private fun adInterstitial(appContext: Context){
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(appContext, "ca-app-pub-3940256099942544/1033173712", adRequest, object: InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(p0: LoadAdError) {
                //super.onAdFailedToLoad(p0)
                Toast.makeText(appContext, "No se pudo cargar el anuncio", Toast.LENGTH_SHORT).show()
                interstitialAd = null
            }

            override fun onAdLoaded(p0: InterstitialAd) {
                Toast.makeText(appContext, "Si se pudo cargar el anuncio", Toast.LENGTH_SHORT).show()
                interstitialAd = p0
            }
        })

        interstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback(){
            override fun onAdClicked() {
                super.onAdClicked()
            }

            override fun onAdDismissedFullScreenContent() {
                interstitialAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                interstitialAd = null
            }

            override fun onAdImpression() {
                super.onAdImpression()
            }

            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()
            }
        }
    }

    //Show Ad INTERSTITIAL
    private fun showAd(){
        if(interstitialAd != null){
            interstitialAd?.show(requireContext() as Activity)
        }
        else{
            Toast.makeText(requireContext(), "No cargará el anuncio", Toast.LENGTH_SHORT).show()
        }
    }


    //Ad BANNER
    private fun adBanner(appContext: Context){
        MobileAds.initialize(appContext)
        var adView = binding.adView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener(){
            override fun onAdClicked() {
                Toast.makeText(appContext, "Haz hecho click", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                Toast.makeText(appContext, "Haz cerrado el Ad", Toast.LENGTH_SHORT).show()
                super.onAdClosed()
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
            }

            override fun onAdImpression() {
                super.onAdImpression()
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
            }

            override fun onAdOpened() {
                super.onAdOpened()
            }

            override fun onAdSwipeGestureClicked() {
                super.onAdSwipeGestureClicked()
            }
        }
    }

}