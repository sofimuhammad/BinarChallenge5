package com.example.binarchallenge5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_result_dialog.view.*

class ResultDialogFragment(
    val winner: String,
    val backToMenu: () -> Unit,
    val playAgain: () -> Unit
) : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.tv_winner.text = "$winner MENANG"

        view.btn_kembali_menu.setOnClickListener {
            this.dismiss()
            backToMenu()
        }

        view.btn_main_lagi.setOnClickListener {
            this.dismiss()
            playAgain()
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}