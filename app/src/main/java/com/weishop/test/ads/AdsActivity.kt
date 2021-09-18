package com.weishop.test.ads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.*
import com.weishop.test.R
import com.weishop.test.util.LogUtils

class AdsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ads)

        MobileAds.initialize(this) {
            LogUtils.d("init=${it}")
        }
        val adView = findViewById<AdView>(R.id.adView)
        adView.adListener = object : AdListener() {

            /**
             * 广告加载结束调用
             */
            override fun onAdLoaded() {
                super.onAdLoaded()
                LogUtils.d("onAdLoaded")
            }

            /**
             * 广告请求失败时候调用
             */
            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                LogUtils.d("onAdFailedToLoad")
            }

            /**
             * 用户点击，广告打开并覆盖屏幕时候调用
             */
            override fun onAdOpened() {
                super.onAdOpened()
                LogUtils.d("onAdOpened")
            }

            /**
             * 用户打算从广告返回app
             */
            override fun onAdClosed() {
                super.onAdClosed()
                LogUtils.d("onAdClosed")
            }

            /**
             * 用户点击广告
             */
            override fun onAdClicked() {
                super.onAdClicked()
                LogUtils.d("onAdClicked")
            }


            /**
             * 广告出现时候调用
             */
            override fun onAdImpression() {
                super.onAdImpression()
                LogUtils.d("onAdImpression")
            }


        }
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

    }
}