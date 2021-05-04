package com.rustedwheel.android.banktransactions

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class BTApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDatabase()
    }

    private fun initDatabase() {
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .inMemory()
                .deleteRealmIfMigrationNeeded()
                .name("main-db")
                .schemaVersion(1)
                .build()
        )
    }
}