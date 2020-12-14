package com.example.binarchallenge5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_battle.*

class BattleActivity : AppCompatActivity() {
    private var isComputer = false
    private var pilihanPlayer1 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        if (intent.hasExtra("isComputer")) {
            isComputer = intent.getBooleanExtra("isComputer", false)
            tv_player_2.text = if (isComputer) {
                "CPU"
            }else{
                "PLAYER 2"
            }
        }

        val controller = Controller(object : Callback {
            override fun tampilkanHasil(hasil: String) {
                //text_vs.text = hasil
                val dialog = ResultDialogFragment(
                    hasil,
                    backToMenu = {
                        finish()
                    },
                    playAgain = {
                        enableOrdisableSemuaTombol(true)
                        text_vs.text = "VS"
                    }
                )
                dialog.show(supportFragmentManager, ResultDialogFragment::class.java.simpleName)
            }

        })

        reset.setOnClickListener {
            enableOrdisableSemuaTombol(true)
            text_vs.text = "VS"
        }

        P_BATU.setOnClickListener {
            if (isComputer) {
                memilihDanProses(controller, pilihan = "batu", pilihan2 ="")
            } else {
                pilihanPlayer1 = "batu"
                setBackgroundSelectedPlayer1(pilihanPlayer1)
                enableOrDisableButtonPlayer1(false)
            }
        }

        P_KERTAS.setOnClickListener {
            if (isComputer) {
                memilihDanProses(controller, pilihan = "kertas", pilihan2 ="")
            } else {
                pilihanPlayer1 = "kertas"
                setBackgroundSelectedPlayer1(pilihanPlayer1)
                enableOrDisableButtonPlayer1(false)
            }
        }

        P_GUNTING.setOnClickListener {
            if (isComputer) {
                memilihDanProses(controller, pilihan = "gunting", pilihan2 ="")
            } else {
                pilihanPlayer1 = "gunting"
                setBackgroundSelectedPlayer1(pilihanPlayer1)
                enableOrDisableButtonPlayer1(false)
            }
        }

        COM_BATU.setOnClickListener {
            memilihDanProses(controller, pilihan = pilihanPlayer1, pilihan2 = "batu")
        }

        COM_GUNTING.setOnClickListener {
            memilihDanProses(controller, pilihan = pilihanPlayer1, pilihan2 = "gunting")
        }

        COM_KERTAS.setOnClickListener {
            memilihDanProses(controller, pilihan = pilihanPlayer1, pilihan2 ="kertas")
        }

        iv_close.setOnClickListener {
            finish()
        }
    }

    //function untuk memproses pilihan player
    private fun memilihDanProses(
        controller: Controller,
        pilihan: String,
        pilihan2: String
    ) {
        val namePlayer2 = if (isComputer) {
            "CPU"
        } else {
            "Player 2"
        }

        val player = Player(nama = PreferencesUtil.getPrefName(this)?:"")
        val comp = Player(nama = namePlayer2)

        player.pilihan = pilihan
        comp.pilihan = if (isComputer) {
            listOf("batu", "gunting", "kertas").shuffled().first()
        }else{
            pilihan2
        }

        Log.e("pilihan", "${player.pilihan}")
        Log.e("pilihan", "${comp.pilihan}")

        enableOrdisableSemuaTombol(false)

        setBackgroundSelectedPlayer1(pilihan)

        when (comp.pilihan) {
            "batu" -> COM_BATU.setBackgroundSelected()
            "gunting" -> COM_GUNTING.setBackgroundSelected()
            "kertas" -> COM_KERTAS.setBackgroundSelected()
        }

        controller.setPlayer1(player)
        controller.setPlayer2(comp)
        controller.proses()
    }

    private fun setBackgroundSelectedPlayer1(pilihan: String) {
        when (pilihan) {
            "batu" -> P_BATU.setBackgroundSelected()
            "gunting" -> P_GUNTING.setBackgroundSelected()
            "kertas" -> P_KERTAS.setBackgroundSelected()
        }
    }

    //function untuk disable atau enable tombolnya
    private fun enableOrdisableSemuaTombol(isEnable: Boolean) {
        enableOrDisableButtonPlayer1(isEnable)

        enableOrDisableButtonPlayer2(isEnable)

        P_BATU.setBackgroundUnselected()
        P_GUNTING.setBackgroundUnselected()
        P_KERTAS.setBackgroundUnselected()

        COM_BATU.setBackgroundUnselected()
        COM_GUNTING.setBackgroundUnselected()
        COM_KERTAS.setBackgroundUnselected()
    }

    private fun enableOrDisableButtonPlayer1(isEnable: Boolean) {
        P_BATU.isEnabled = isEnable
        P_GUNTING.isEnabled = isEnable
        P_KERTAS.isEnabled = isEnable
    }

    private fun enableOrDisableButtonPlayer2(isEnable: Boolean) {
        COM_BATU.isEnabled = isEnable
        COM_GUNTING.isEnabled = isEnable
        COM_KERTAS.isEnabled = isEnable
    }

    //membuat setiap background komponen dengan warna yg sama
    private fun View.setBackgroundSelected() {
        this.setBackgroundColor(ContextCompat.getColor(this@BattleActivity, R.color.colorAccent))
    }

    private fun View.setBackgroundUnselected() {
        this.setBackgroundColor(
            ContextCompat.getColor(
                this@BattleActivity,
                android.R.color.transparent
            )
        )
    }
}