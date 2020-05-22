package com.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mvpbook.strorage.SharedPrefManager
import com.testapp.app.regularEmail
import com.testapp.app.regularPass
import com.testapp.room.Users_db
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    private val sh = SharedPrefManager.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Users_db.get(application)

        if (sh.isLoggedIn) {
            nextPage()
        } else if (!sh.isLoggedIn) {
            genUsers()
        }

        sumbit_btn.setOnClickListener {
            check()
        }
    }

    fun sumbit(check: Boolean) {
        if (check) {
            sh.setLogged(true)
            nextPage()
        } else {
            Toast.makeText(this, "Вы ввели неверные данные", Toast.LENGTH_LONG).show()
        }
    }

    fun nextPage() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun check() {
        val email = email_text.text.toString()
        val pass = pass_text.text.toString()
        if (!regularEmail(email)) {
            email_text.error = "Неверный email"
            return
        }
        if (!regularPass(pass)) {
            pass_text.error = "Не соотвествует паттерну"
            return
        }

        Users_db.get(application).getUserDao().checkPass(email, pass).observe(this,
            Observer {
                Log.e("N!!q",it+"q")
                val checkRes = email.equals(it)
                if (checkRes) {
                    SharedPrefManager.getInstance(application).saveUserData(email, pass)
                }
                runOnUiThread { sumbit(checkRes) }
            })

    }


    fun genUsers() {
        val genUsers = listOf(
            User(
                0,
                "test@gmail.com",
                "Test",
                "Test",
                "https://welcome-test.ru/img/welcome_test/new/logo.png",
                "Testpass0"
            ),
            User(
                1,
                "bernard@gmail.com",
                "Bernard",
                "Simpson",
                "https://randomuser.me/api/portraits/men/83.jpg",
                "pE1a98N"
            ),
            User(
                2,
                "stephens@gmail.dot.com",
                "Same",
                "Stephens",
                "https://randomuser.me/api/portraits/men/79.jpg",
                "ZTx6hFh"
            ),
            User(
                3,
                "morris@gmail.com",
                "Wayne",
                "Morris",
                "https://randomuser.me/api/portraits/men/22.jpg",
                "9mngURa"
            )
        )

        doAsync {
            Users_db.get(application).getUserDao().insert(genUsers)
        }

    }


}
