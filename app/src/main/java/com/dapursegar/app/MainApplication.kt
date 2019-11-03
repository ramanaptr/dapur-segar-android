package com.dapursegar.app

import android.app.Application
import com.dapursegar.app.core.database.MovieDatabase
import com.dapursegar.app.injection.getAllModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    companion object {
        private var mInstance: MainApplication? = null
        private var db: MovieDatabase? = null

        @Synchronized
        fun getInstance(): MainApplication? {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        startKoinInjection()
        // startDatabase()
    }

    private fun startKoinInjection() {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(getAllModules())
        }
    }

    private fun startDatabase() {
        db = MovieDatabase.init(applicationContext)
    }

    fun getDatabase(): MovieDatabase? {
        return db
    }
}
