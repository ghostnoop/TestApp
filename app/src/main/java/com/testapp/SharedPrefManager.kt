package com.mvpbook.strorage

import android.annotation.SuppressLint
import android.content.Context


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SharedPrefManager private constructor(private val mCtx: Context) {


    val isLoggedIn: Boolean
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean("islogged", false)
        }
    val firstTime: Boolean
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean("firstTime", true)
        }
    val userData: List<String>
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return listOf(
                sharedPreferences.getString("email", "").toString(),
                sharedPreferences.getString("pass", "").toString()
            )
        }


    fun setLogged(isLogged: Boolean) {
        val sharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("islogged", isLogged)
        editor.apply()
    }

    fun setFirstTime() {
        val sharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("firstTime", false)
        editor.apply()
    }

    fun saveUserData(email: String, pass: String) {
        val sharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("pass", pass)
        editor.apply()
    }


    fun clear() {
        val sharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    //
    companion object {
        val SHARED_PREF_NAME = "my_shared_preff"
        private val SHARED_PREF_ADVER = "my_shared_adver"

        @SuppressLint("StaticFieldLeak")
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}