package com.frantun.androidottokotlin

import android.app.Application
import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer

class App : Application() {

    var bus = Bus(ThreadEnforcer.MAIN)

    override fun onCreate() {
        super.onCreate()
    }
}