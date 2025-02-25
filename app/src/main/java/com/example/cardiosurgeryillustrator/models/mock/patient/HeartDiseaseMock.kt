package com.example.cardiosurgeryillustrator.models.mock.patient

import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.models.patient.home.HeartDisease
import com.example.cardiosurgeryillustrator.models.patient.home.Point

val diseases = listOf(
    HeartDisease(
        name = "Doença Coronariana (entupimento das artérias do coração)",
        points = listOf(
            Point(xOffset = 130.dp, yOffset = 80.dp, route = "artery_details/right_coronary"),
            Point(xOffset = 250.dp, yOffset = 115.dp, route = "artery_details/left_coronary")
        )
    ),
    HeartDisease(
        name = "Valvopatia Mitral (problemas na válvula mitral)",
        points = listOf(
            Point(xOffset = 120.dp, yOffset = 150.dp, route = "artery_details/mitral_valve")
        )
    ),
    HeartDisease(
        name = "Valvopatia Aórtica (problemas na válvula aórtica)",
        points = listOf(
            Point(xOffset = 180.dp, yOffset = 60.dp, route = "artery_details/aortic_valve")
        )
    ),
    HeartDisease(
        name = "Valvopatia Tricúspide (problemas na válvula tricúspide)",
        points = listOf(
            Point(xOffset = 250.dp, yOffset = 180.dp, route = "artery_details/tricuspid")
        )
    ),
    HeartDisease(
        name = "Dissecção Aórtica",
        points = listOf(
            Point(xOffset = 200.dp, yOffset = 50.dp, route = "artery_details/aortic_dissection")
        )
    ),
    HeartDisease(
        name = "Aneurisma de Aorta Torácica",
        points = listOf(
            Point(xOffset = 160.dp, yOffset = 40.dp, route = "artery_details/thoracic_aneurysm")
        )
    ),
    HeartDisease(
        name = "Aneurisma de Aorta Abdominal",
        points = listOf(
            Point(xOffset = 190.dp, yOffset = 250.dp, route = "artery_details/abdominal_aneurysm")
        )
    ),
    HeartDisease(
        name = "Miocardiopatia Diluída ou Hipertrófica",
        points = listOf(
            Point(xOffset = 170.dp, yOffset = 130.dp, route = "artery_details/myocardial_disease")
        )
    )
)
