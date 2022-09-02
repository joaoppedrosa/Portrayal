package com.jppedrosa.portrayal

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 02/09/2022.
 */

@HiltAndroidApp
class PortrayalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}