package com.weishop.test.ads

import com.google.android.gms.ads.interstitial.InterstitialAd
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.weishop.test.R
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.weishop.test.util.LogUtils
import kotlinx.android.synthetic.main.activity_interstitial_ads.*

/**
 * 插屏广告(Interstitial ads )
 */
class InterstitialActivity : AppCompatActivity(), View.OnClickListener {
    private var mInterstitialAd: InterstitialAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial_ads)
        show.setOnClickListener(this)
        MobileAds.initialize(this) { }

        load()
    }

    private fun load() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    // The mInterstitialAd reference will be null until
                    // an ad is loaded.
                    LogUtils.d("onAdLoaded")
                    mInterstitialAd = interstitialAd

                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    // Handle the error
                    LogUtils.d("onAdFailedToLoad")
                    mInterstitialAd = null
                }
            })
    }

    override fun onClick(v: View?) {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                super.onAdFailedToShowFullScreenContent(p0)
                LogUtils.d("onAdFailedToShowFullScreenContent")
                mInterstitialAd = null//展示失败，置为null
            }

            /**
             * 全屏内容展示的时候调用
             */
            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()
                LogUtils.d("onAdShowedFullScreenContent")
            }

            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                LogUtils.d("onAdDismissedFullScreenContent")
                mInterstitialAd = null//结束以后不再展示的话，要置为null
                load()
            }

            override fun onAdImpression() {
                super.onAdImpression()
                LogUtils.d("onAdImpression")
            }
        }

        //重复调用不会展示，必须再重新加载
        mInterstitialAd?.show(this)
    }
}