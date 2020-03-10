package tat.mukhutdinov.kittygram.infrastructure

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import tat.mukhutdinov.kittygram.Database

actual fun provideSqlDriver(): SqlDriver =
    NativeSqliteDriver(Database.Schema, "kittyGram.db")