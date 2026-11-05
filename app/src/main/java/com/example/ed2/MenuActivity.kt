package com.example.ed2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {

    private lateinit var btnPresion : Button
    private lateinit var btnGiros : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        btnPresion = findViewById(R.id.btnPresion)
        btnGiros = findViewById(R.id.btnGiros)

        btnPresion.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        btnGiros.setOnClickListener {

            val intent = Intent(this, GirosActivity::class.java)
            startActivity(intent)

        }




    }
}