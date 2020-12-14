package com.example.binarchallenge5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.learnclass6.SecondFragment

class ViewPagerAdapter(
    fa: FragmentActivity,
    listener: (CharSequence) -> Unit
) : FragmentStateAdapter(fa) {
    private val datafragments = mutableListOf(
        SecondFragment.newInstance("Bermain suit melawan sesama pemain.", 1, listener),
        SecondFragment.newInstance("Bermain suit melawan komputer", 2, listener),
        SecondFragment.newInstance("Tuliskan namamu di bawah", 3, listener)
    )

    override fun getItemCount(): Int = datafragments.size

    override fun createFragment(position: Int): Fragment {
        return datafragments[position]
    }

}