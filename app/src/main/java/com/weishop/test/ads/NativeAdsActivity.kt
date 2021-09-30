package com.weishop.test.ads

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.weishop.test.R
import com.weishop.test.util.LogUtils
import kotlinx.android.synthetic.main.activity_interstitial_ads.*

/**
 * 原生广告(native ads )
 */
class NativeAdsActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ads)
        show.setOnClickListener(this)
        MobileAds.initialize(this) { }

        load()
    }

    private fun load() {
        val adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110").forNativeAd {
            LogUtils.d("forUnifiedNativeAd success")

        }.withAdListener(object : AdListener() {
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

        }).build()
        adLoader.loadAd(AdRequest.Builder().build())

    }

    override fun onClick(v: View?) {

    }
}