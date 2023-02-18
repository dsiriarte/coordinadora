package com.davidsantiagoiriarte.domain.model

data class Cliente(
    val documento : String,
    val guias: List<Guia>
)
