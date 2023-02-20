package com.davidsantiagoiriarte.presentation

import com.davidsantiagoiriarte.domain.model.Destinatario
import com.davidsantiagoiriarte.domain.model.Zonificacion
import com.davidsantiagoiriarte.presentation.model.ViewGuia
import com.davidsantiagoiriarte.presentation.ui.theme.enNySColor
import com.davidsantiagoiriarte.presentation.ui.theme.enRepartoColor
import com.davidsantiagoiriarte.presentation.ui.theme.enTerminalColor
import com.davidsantiagoiriarte.presentation.ui.theme.entregadaColor

val fakeGuias = listOf(
    ViewGuia(
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
        R.drawable.reparto,
        enRepartoColor,
        "15/01/2023",
        "01860068473",
        1,
        null,
        null,
        listOf()
    ),
    ViewGuia(
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
        R.drawable.nys,
        enNySColor,
        "15/01/2023",
        "01860031234",
        1,
        null,
        null,
        listOf()
    ),
    ViewGuia(
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
        R.drawable.terminal,
        enTerminalColor,
        "23/01/2023",
        "01860055432",
        1,
        null,
        null,
        listOf()
    ),
    ViewGuia(
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
        R.drawable.entregada,
        entregadaColor,
        "10/01/2023",
        "01860055432",
        1,
        null,
        null,
        listOf()
    )
)
