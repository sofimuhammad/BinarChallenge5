package com.example.binarchallenge5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = PreferencesUtil.getPrefName(this)?.toUpperCase()

        Toast.makeText(this, "Selamat Datang $name", Toast.LENGTH_SHORT).show()

        tv_player_player.text = String.format(getString(R.string.ervin_vs_pemain), name)
        tv_player_comp.text = String.format(getString(R.string.ervin_vs_cpu), name)

        ll_player_comp.setOnClickListener {
            val intent = Intent(this, BattleActivity::class.java).apply {
                putExtra("isComputer", true)
            }
            startActivity(intent)
        }

        ll_player_player.setOnClickListener {
            val intent = Intent(this, BattleActivity::class.java).apply {
                putExtra("isComputer", false)
            }
            startActivity(intent)
        }
    }
}