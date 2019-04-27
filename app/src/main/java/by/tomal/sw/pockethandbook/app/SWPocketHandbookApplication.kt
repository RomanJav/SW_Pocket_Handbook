package by.tomal.sw.pockethandbook.app

import android.app.Application

class SWPocketHandbookApplication : Application() {

    companion object {
        lateinit var instance: SWPocketHandbookApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}