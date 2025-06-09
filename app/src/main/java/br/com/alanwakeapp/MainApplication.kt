package br.com.alanwakeapp

import android.app.Application
import br.com.alanwakeapp.di.appModule
import br.com.alanwakeapp.di.dataModule
import br.com.alanwakeapp.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule, domainModule, dataModule)
        }
    }
}