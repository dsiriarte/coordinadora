package com.davidsantiagoiriarte.data.localstorage

import androidx.room.Database
import com.davidsantiagoiriarte.data.localstorage.daos.ClientesDao
import com.davidsantiagoiriarte.data.localstorage.entities.*

@Database(
    entities = [
        DBCliente::class,
        DBGuia::class,
        DBDestinatario::class,
        DBZonificacion::class,
        DBUnidad::class
    ],
    version = 1
)
abstract class LocalDB {
    abstract fun clientesDao(): ClientesDao
}
