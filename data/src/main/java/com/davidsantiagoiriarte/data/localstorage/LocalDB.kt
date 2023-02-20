package com.davidsantiagoiriarte.data.localstorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidsantiagoiriarte.data.localstorage.daos.GuiasDao
import com.davidsantiagoiriarte.data.localstorage.daos.UnidadesDao
import com.davidsantiagoiriarte.data.localstorage.entities.*

@Database(
    entities = [
        DBGuia::class,
        DBUnidad::class
    ],
    version = 1
)
abstract class LocalDB : RoomDatabase() {
    abstract fun guiasDao(): GuiasDao
    abstract fun unidadesDao(): UnidadesDao
}
