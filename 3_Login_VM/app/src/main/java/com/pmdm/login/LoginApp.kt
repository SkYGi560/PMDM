package com.pmdm.login

import android.app.Application

class LoginApp  : Application() {
    companion object {
        lateinit var instance: LoginApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
