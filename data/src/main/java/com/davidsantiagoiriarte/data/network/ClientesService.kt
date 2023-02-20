package com.davidsantiagoiriarte.data.network

import retrofit2.http.GET

interface ClientesService {

    @GET("api")
    suspend fun getClientes(): APIClientes
}
