package com.example.binarchallenge5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_landing_page.*

class LandingPage : AppCompatActivity() {
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        val viewPagerAdapter = ViewPagerAdapter(this) {
            iv_arrow_next.visibility = if (it.isNotBlank()) {
                View.VISIBLE
            } else {
                View.GONE
            }
            name = it.toString()
        }
        viewpager2.adapter = viewPagerAdapter
        dots_indicator.setViewPager2(viewpager2)

        iv_arrow_next.setOnClickListener {
            PreferencesUtil.setPrefName(name, this)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}