package com.example.practicemvvm.ui

import android.content.Context
import android.widget.Toast
import com.example.practicemvvm.databinding.FragmentProductDetailBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class AdsBanner {
    fun adBanner(appContext: Context, binding: FragmentProductDetailBinding){
        MobileAds.initialize(appContext)
        val adView = binding.adView
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