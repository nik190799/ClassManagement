package com.creedeon.creed003

import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.class_info.*

class ClassInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_info)
        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        val myadapter = SlideAdapter(this)
        viewPager.adapter = myadapter
        class_info_about_us_btn.setOnClickListener {
            startActivity(Intent(this,ClassInfoAboutUs::class.java))
        }
    }
}
