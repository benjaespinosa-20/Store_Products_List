package com.example.practicemvvm.ui

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdsInterstitial {
    private var interstitialAd: InterstitialAd? = null

    //Ad INTERSTITIAL
    fun adInterstitial(appContext: Context){
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
    fun showAdInterstitial(requireContext: Context){
        if(interstitialAd != null){
            interstitialAd?.show(requireContext as Activity)
        }
        else{
            Toast.makeText(requireContext, "No cargará el anuncio", Toast.LENGTH_SHORT).show()
        }
    }
}