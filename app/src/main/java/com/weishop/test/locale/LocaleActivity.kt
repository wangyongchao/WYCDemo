package com.weishop.test.locale

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.databinding.ActivityLocaleBinding
import com.weishop.test.databinding.ActivityViewmodelLivedataBinding
import com.weishop.test.util.LogUtils
import java.util.*

class LocaleActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLocaleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLocaleBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.get.setOnClickListener {
            printLocale()
        }

    }

    fun printLocale() {
        var loc=this.resources.configuration.locale
        val languageTag = System.getProperty("user.locale", "")
        val language = System.getProperty("user.language", "en")
        val region = System.getProperty("user.region")

        val script = System.getProperty("user.script", "")
        val country = System.getProperty("user.country", "")
        val variant = System.getProperty("user.variant", "")
        println("languageTag=$languageTag,language=$language,region=$region,script=$script,country=$country" +
                ",variant=$variant,localelanguage=${loc.language},localecountry=${loc.country}")
    }

    fun initDefault(): Locale? {
        // BEGIN Android-added: In initDefault(), prioritize user.locale.
        // user.locale gets priority
        val languageTag = System.getProperty("user.locale", "")
        if (!languageTag.isEmpty()) {
            return Locale.forLanguageTag(languageTag)
        }
        // END Android-added: In initDefault(), prioritize user.locale.

        // BEGIN Android-changed: Short-circuit legacy security code.
        // Use System.getProperty(name, default) instead of
        // AccessController.doPrivileged(new GetPropertyAction(name, default)).
        val language: String
        val region: String?
        val script: String
        val country: String
        val variant: String
        language = System.getProperty("user.language", "en")
        // for compatibility, check for old user.region property
        region = System.getProperty("user.region")
        if (region != null) {
            // region can be of form country, country_variant, or _variant
            val i = region.indexOf('_')
            if (i >= 0) {
                country = region.substring(0, i)
                variant = region.substring(i + 1)
            } else {
                country = region
                variant = ""
            }
            script = ""
        } else {
            script = System.getProperty("user.script", "")
            country = System.getProperty("user.country", "")
            variant = System.getProperty("user.variant", "")
        }
        // END Android-changed: Short-circuit legacy security code.
        return Locale(language, country, variant)
    }

}
