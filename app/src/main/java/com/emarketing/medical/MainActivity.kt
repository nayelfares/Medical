package com.emarketing.medical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emarketing.medical.ui.GeneralBrowsing
import com.emarketing.medical.ui.Register
import com.emarketing.medical.ui.Search
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        register.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
        search.setOnClickListener { startActivity(Intent(this,Search::class.java)) }
        generalBrowsing.setOnClickListener { startActivity(Intent(this,GeneralBrowsing::class.java)) }
    }
}