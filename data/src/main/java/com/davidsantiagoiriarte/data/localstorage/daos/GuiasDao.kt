package com.davidsantiagoiriarte.data.localstorage.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davidsantiagoiriarte.data.localstorage.entities.DBGuia
import com.davidsantiagoiriarte.data.localstorage.entities.DBGuiaConUnidades
import kotlinx.coroutines.flow.Flow

@Dao
interface GuiasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(guia: DBGuia)

    @Query("SELECT * FROM DBGuia WHERE identificacion_cliente = :identificacionCliente")
    fun buscarGuiasCliente(identificacionCliente: String): Flow<List<DBGuiaConUnidades>>


    @Query("SELECT * FROM DBGuia WHERE guia = :numeroGuia")
    suspend fun buscarGuia(numeroGuia: String): DBGuiaConUnidades
}
