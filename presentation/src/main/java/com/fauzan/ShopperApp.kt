package com.fauzan

import android.app.Application
import com.fauzan.data.di.dataModule
import com.fauzan.di.presentationModule
import com.fauzan.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShopperApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopperApp)
            modules(listOf(
                presentationModule,
                dataModule,
                domainModule
            ))
        }
    }
}