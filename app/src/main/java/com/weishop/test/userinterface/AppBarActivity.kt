package com.weishop.test.userinterface

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.weishop.test.R
import com.weishop.test.activitycharacter.BActivity

class AppBarActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appbar)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        window?.decorView?.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
        }
        findViewById<Button>(R.id.btn).setOnClickListener {
            startActivity(Intent(this@AppBarActivity,AppBarChildActivity::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {}
}