package com.testapp.app

fun regularEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun regularPass(pass: String): Boolean {
    val regex = Regex(pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{6,}")
    return regex.matches(input = pass)
}