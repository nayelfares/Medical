package com.emarketing.medical

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.TextPaint
import androidx.appcompat.app.AppCompatActivity
import com.emarketing.medical.ui.ArticalsActivity
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
            val intent=Intent(this,ArticalsActivity::class.java)
            intent.putExtra("catName",resources.getString(R.string.child_health))
            intent.putExtra("catId",2)
            startActivity(intent)
        }
        chilrenBehavior.setOnClickListener {
            val intent=Intent(this,ArticalsActivity::class.java)
            intent.putExtra("catName",resources.getString(R.string.children_behavior))
            intent.putExtra("catId",1)
            startActivity(intent)
        }
        consultants.setOnClickListener { startActivity(
            Intent(
                this,
                ArticalsActivity::class.java
            )
        ) }
    }
}