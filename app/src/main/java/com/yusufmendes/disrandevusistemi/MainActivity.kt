package com.yusufmendes.disrandevusistemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize Firebase Auth
        auth = Firebase.auth

        //kullanici daha once giris yapmis ise uygulama direk FeedActivity ekrani ile acilir
        val currentUser = auth.currentUser
        if (currentUser != null){

            val intent = Intent(this@MainActivity, TabView::class.java)
            startActivity(intent)
            finish()
        }


    }

    fun girisYap(view : View){

        val email = emailEditText.text.toString()
        val password = sifreEditText.text.toString()

        //kullanici email ve sifresini girmis ise yapilacak islemler
        if (email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                //giris yapma islemi basarili ise olusturulan TabView activity'e gitme islemi yapildi
                val intent = Intent(this@MainActivity, TabView::class.java)
                startActivity(intent)
                finish()

            }   //giris basarisiz ise hata mesajini toast mesajda gosterilecek
                .addOnFailureListener {

                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
        //eger kullanici email ve sifresini girmemis ise kullanici toast mesaj ile email ve sifresini girmesi mesaji verildi
        else{
            Toast.makeText(this,"Email ve şifrenizi giriniz!",Toast.LENGTH_LONG).show()
        }

    }

    fun kaydol(view : View){

        val email = emailEditText.text.toString()
        val password = sifreEditText.text.toString()

        //email ve password edit texte deger girilip girilmedigi kontrol edildi
        //bos degilse yapilacaklar
        if (email.isNotEmpty() && password.isNotEmpty()){

            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                //basarili olunca yapilacaklar

                val intent = Intent(this@MainActivity, TabView::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener {

                //basarisiz olunca yapilacaklar
                //lacalizedMessage -> kullanicinin anlayacagi sekilde hatayi goster
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()

                println(it.localizedMessage)

            }
        }
        //bos ise yapilacaklar
        else{

            Toast.makeText(this@MainActivity,"Email ve şifrenizi giriniz!",Toast.LENGTH_LONG).show()
        }
    }
}