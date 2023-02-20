package com.davidsantiagoiriarte.domain

import com.davidsantiagoiriarte.domain.model.Destinatario
import com.davidsantiagoiriarte.domain.model.Guia
import com.davidsantiagoiriarte.domain.model.Unidad
import com.davidsantiagoiriarte.domain.model.Zonificacion
import com.davidsantiagoiriarte.domain.repository.GuiasRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

val fakeGuias = listOf(
    Guia(
        Destinatario(
            "R/SENTACIONES CASTRO HNOS LTDA",
            "123",
            "",
            Zonificacion(
                "Ibague",
                "1",
                "",
                "Cra 1",
                "",
                null,
                null,
                "",
                "73"
            )
        ),
        "En Reparto",
        "15/01/2023",
        "01860068473",
        1,
        null,
        null,
        listOf()
    ),
    Guia(
        Destinatario(
            "FERNANDO CASTRILLON",
            "123",
            "",
            Zonificacion(
                "Ibague",
                "1",
                "",
                "Cra 1",
                "",
                null,
                null,
                "",
                "73"
            )
        ),
        "En NyS",
        "15/01/2023",
        "01860031234",
        1,
        null,
        null,
        listOf()
    ),
    Guia(
        Destinatario(
            "DIANA BERENISE ORTEGA",
            "123",
            "",
            Zonificacion(
                "Ibague",
                "1",
                "",
                "Cra 1",
                "",
                null,
                null,
                "",
                "73"
            )
        ),
        "En terminal",
        "23/01/2023",
        "01860055432",
        1,
        null,
        null,
        listOf()
    ),
    Guia(
        Destinatario(
            "DIANA BERENISE ORTEGA",
            "123",
            "",
            Zonificacion(
                "Ibague",
                "1",
                "",
                "Cra 1",
                "",
                null,
                null,
                "",
                "73"
            )
        ),
        "Entregada",
        "10/01/2023",
        "01860055432",
        1,
        null,
        null,
        listOf()
    )
)

val fakeDestinatario = Destinatario(
    "DIANA BERENISE ORTEGA",
    "123",
    "",
    Zonificacion(
        "Ibague",
        "1",
        "",
        "Cra 1",
        "",
        null,
        null,
        "",
        "73"
    )
)

val fakeUnidad = Unidad(
    "701860068473001",
    "7001001200002100001860068473001",
    "01860055432",
    1,
    "01860068473.1"
)

object FakeGuiasRepository : GuiasRepository {
    override suspend fun sincronizarGuias() {
    }

    override fun buscarGuiasCliente(identificacionCliente: String): Flow<List<Guia>> {
        return MutableStateFlow(fakeGuias)
    }

    override suspend fun buscarGuia(numeroGuia: String): Guia {
        return fakeGuias[0]
    }
}
