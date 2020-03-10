package tat.mukhutdinov.kittygram.infrastructure

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import tat.mukhutdinov.kittygram.Database

lateinit var application: Application

actual fun provideSqlDriver(): SqlDriver =
    AndroidSqliteDriver(Database.Schema, application, "kittyGram.db")