package com.example.cardiosurgeryillustrator.ui.screens.patient.nearby_clinics

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cardiosurgeryillustrator.core.services.fetchNearbyClinics
import com.example.cardiosurgeryillustrator.models.mock.patient.mockClinics
import com.example.cardiosurgeryillustrator.models.patient.nearby_clinics.Clinic
import com.example.cardiosurgeryillustrator.models.patient.nearby_clinics.Element
import com.example.cardiosurgeryillustrator.ui.components.patient.nearby_clinics.ClinicCardList
import com.example.cardiosurgeryillustrator.ui.theme.Zinc100
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun NearbyClinics(modifier: Modifier, navController: NavController) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    var userLocation by remember {
        mutableStateOf(LatLng(-4.979093146453051, -39.05652788958835))
    }

    var clinicsList by remember { mutableStateOf(emptyList<Element>()) }

    val markerState = remember(userLocation) { MarkerState(userLocation) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 14f)
    }

    // Lembre-se de parar as atualizações quando o Composable for destruído
    LaunchedEffect(locationPermissionState.allPermissionsGranted) {
        if (locationPermissionState.allPermissionsGranted) {
            startLocationUpdates(fusedLocationClient) { location ->
                val newLocation = LatLng(location.latitude, location.longitude)

                // Atualiza a localização
                userLocation = newLocation

                // Move a câmera para a nova localização
                cameraPositionState.position = CameraPosition.fromLatLngZoom(userLocation, 14f)

                fetchNearbyClinics(location.latitude, location.longitude) { clinics ->
                    clinicsList = clinics // Atualiza a lista de clínicas com os resultados
                }
            }
        }
    }

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetContainerColor = Zinc100,
        sheetPeekHeight = LocalConfiguration.current.screenHeightDp.dp * 0.5f,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            ClinicCardList(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                clinics = convertElementToClinic(clinicsList)
            )
        },
        topBar = {
            TopAppBar(
                title = { Text("Clínicas Próximas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        },
        content = {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(
                        bottom = it
                            .calculateBottomPadding()
                            .minus(8.dp)
                    )
            ) {
                GoogleMap(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(LocalConfiguration.current.screenHeightDp.dp * 0.5f),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = markerState,
                        title = "Sua localização"
                    )

                    clinicsList.forEach { clinic ->
                        Marker(
                            state = MarkerState(position = LatLng(clinic.lat, clinic.lon)),
                            title = clinic.tags["name"] ?: "Clínica",
                            snippet = clinic.tags["address"] ?: "Endereço desconhecido"
                        )
                    }
                }
            }
        }
    )
}

@SuppressLint("MissingPermission")
private fun startLocationUpdates(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationReceived: (android.location.Location) -> Unit
) {
    val locationRequest = com.google.android.gms.location.LocationRequest.Builder(
        com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY, 5000
    ).setMinUpdateIntervalMillis(2000).build()

    val locationCallback = object : com.google.android.gms.location.LocationCallback() {
        override fun onLocationResult(locationResult: com.google.android.gms.location.LocationResult) {
            locationResult.lastLocation?.let { onLocationReceived(it) }
        }
    }

    fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
}


fun Element.toClinic(): Clinic {
    return Clinic(
        id = id.toString(), // Transformando o id Long para String
        name = tags["name"] ?: "Nome não disponível", // Usando o nome de "tags", se existir
        description = tags["description"] ?: "Descrição não disponível", // Usando descrição de "tags", se existir
        latitude = lat,
        longitude = lon,
        address = tags["address"] ?: "Endereço não informado", // Usando endereço de "tags", se existir
        phone = tags["phone"] ?: "Contato não informado" // Usando telefone de "tags", se existir
    )
}

fun convertElementToClinic(elements: List<Element>): List<Clinic> {
    return elements.map { it.toClinic() }
}




@Preview
@Composable
private fun MapsScreenPreview() {
    NearbyClinics(navController = rememberNavController(), modifier = Modifier)
}