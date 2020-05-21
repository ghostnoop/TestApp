package com.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.mvpbook.strorage.SharedPrefManager
import com.testapp.room.Users_db
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setUI()
    }

    fun setUI() {
        val list = SharedPrefManager.getInstance(this).userData
        Users_db.get(application).getUserDao().getUser(list[0], list[1]).observe(this, Observer {
            email_tv.text = it.email
            name_tv.text = it.name
            lastname_tv.text = it.lastname
            Log.e("NN!!profile",it.photo)
                Glide.with(application)
                    .load(it.photo)
                    .placeholder(R.drawable.ic_account_circle_black_24dp)
                    .fitCenter()
                    .into(avatar_iv)
        })

        sign_out_btn.setOnClickListener {
            SharedPrefManager.getInstance(this).setLogged(false)
            back()
        }
    }

    fun back() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
