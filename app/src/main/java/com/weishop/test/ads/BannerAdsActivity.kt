package com.weishop.test.ads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import com.google.android.gms.ads.*
import com.weishop.test.R
import com.weishop.test.util.LogUtils
import kotlinx.android.synthetic.main.activity_banner_ads.*

/**
 * 横幅广告 Banner Ads
 */
class BannerAdsActivity : AppCompatActivity() {
    private lateinit var adView: AdView
    private val needAuto = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_ads)

        MobileAds.initialize(this) {
            LogUtils.d("init=${it}")
        }
        adView = AdView(this)
        ad_container.addView(adView)
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
        adView.adUnitId="ca-app-pub-3940256099942544/6300978111"
        if(needAuto){
            //自适应广告
            adView.adSize = adSize
        }else{
            adView.adSize=AdSize.BANNER
        }
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

    }

    override fun onResume() {
        super.onResume()
        adView.resume()
    }

    override fun onPause() {
        super.onPause()
        adView.pause()
    }

    /**
     * 退出的时候要销毁
     */
    override fun onDestroy() {
        super.onDestroy()
        adView.destroy()
    }

    //自适应横幅广告,采用固定宽高比。
    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density

            var adWidthPixels = ad_container.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }

            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
        }
}