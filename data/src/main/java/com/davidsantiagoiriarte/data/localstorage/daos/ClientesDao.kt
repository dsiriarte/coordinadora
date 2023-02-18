package com.davidsantiagoiriarte.data.localstorage.daos

import androidx.room.*
import com.davidsantiagoiriarte.data.localstorage.entities.DBCliente

@Dao
interface ClientesDao {

    @Query("SELECT * FROM DBCliente WHERE identificacion = :searchQuery")
    suspend fun buscarCliente(searchQuery: String): DBCliente

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(clientes: List<DBCliente>)

    @Update
    suspend fun update(cliente: DBCliente)

}
