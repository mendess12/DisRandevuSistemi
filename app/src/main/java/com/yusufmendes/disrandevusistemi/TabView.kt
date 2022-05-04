package com.yusufmendes.disrandevusistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TableLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yusufmendes.disrandevusistemi.Adapter.MyAdapter
import kotlinx.android.synthetic.main.activity_tab_view.*

class TabView : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_view)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //tabLayout'a tab'ler eklendi
        tabLayout.addTab(tabLayout.newTab().setText("Randevularım"))
        tabLayout.addTab(tabLayout.newTab().setText("Randevu Oluştur"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        //baglama islemleri yapildi
        val adapter = MyAdapter(this,supportFragmentManager,tabLayout.tabCount)
        viewPager.adapter = adapter

        //olusturulan tab'ler arasi gecis yapildiginda yapilacak islemler ayarlandi
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object :  TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                 viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }


        })

    }

    //menu baglama islemleri
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    //menu-> item tiklaninca yapilacak islemler
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.cikisYap) {
            //kullanici hesabindan cikis yapildi
            auth.signOut()
            //Login ekranina gecis yapildi
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}