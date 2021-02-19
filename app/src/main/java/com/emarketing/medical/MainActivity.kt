package com.emarketing.medical

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.TextPaint
import androidx.appcompat.app.AppCompatActivity
import com.emarketing.medical.ui.GeneralBrowsing
import com.emarketing.medical.ui.Register
import com.emarketing.medical.ui.Search
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paint: TextPaint = appName.paint
        val width = paint.measureText(appName.text.toString())

        val textShader: Shader = LinearGradient(
            0f, 0f, width, appName.textSize, intArrayOf(
                Color.parseColor("#ee0979"),
                Color.parseColor("#ff6a00")
            ), null, Shader.TileMode.CLAMP
        )
        appName.paint.shader = textShader
        childHealth.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
        chilrenBehavior.setOnClickListener { startActivity(Intent(this, Search::class.java)) }
        consultants.setOnClickListener { startActivity(
            Intent(
                this,
                GeneralBrowsing::class.java
            )
        ) }
    }
}