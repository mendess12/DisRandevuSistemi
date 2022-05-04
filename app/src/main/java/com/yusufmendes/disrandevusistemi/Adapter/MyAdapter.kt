package com.yusufmendes.disrandevusistemi.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yusufmendes.disrandevusistemi.Fragments.RandevuOlustur
import com.yusufmendes.disrandevusistemi.Fragments.Randevularim

internal class MyAdapter(var context : Context, fragmentManager: FragmentManager, var totalTabs : Int): FragmentPagerAdapter(fragmentManager) {


    // position'a gore hangi activity'nin acilacagi ayarlandi
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                Randevularim()
            }
            1 -> {
                RandevuOlustur()
            }
            else -> getItem(position)
            }
        }
    override fun getCount(): Int {
        return totalTabs
    }

    }