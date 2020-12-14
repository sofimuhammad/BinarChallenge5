package com.example.learnclass6

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.binarchallenge5.R
import kotlinx.android.synthetic.main.fragment_second.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : Fragment() {
    private var param1: String? = null
    private var param2: Int = 0
    private lateinit var listener: (CharSequence) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val title by lazy { view.findViewById.tex() }
        title.text = param1
        when (param2) {
            1 -> {
                image.setBackgroundResource(R.drawable.ic_landing_page1)
                EtFragment.visibility = View.GONE
            }
            2 -> {
                image.setBackgroundResource(R.drawable.ic_landing_page2)
                EtFragment.visibility = View.GONE
            }
            3 -> {
               image.setBackgroundResource(R.drawable.ic_landing_page3)
                EtFragment.visibility = View.VISIBLE
                EtFragment.addTextChangedListener(object : TextWatcher{
                    override fun afterTextChanged(s: Editable?) {

                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (s != null) {
                            listener(s)
                        }
                    }

                })
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, position:Int,listener: (CharSequence) -> Unit) =
            SecondFragment().apply {
                this.listener = listener
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, position)
                }
            }
    }
}