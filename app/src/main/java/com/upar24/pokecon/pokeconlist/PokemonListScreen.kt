package com.upar24.pokecon.pokeconlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.google.accompanist.coil.CoilImage
import com.upar24.pokecon.data.model.PokemonListEntry

@Composable
fun PokemonListScreen(
    navController: NavController
){
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text="~ PokeCon ~",
                style= MaterialTheme.typography.h5,
                modifier=Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("") },
                modifier = Modifier.fillMaxWidth().align(CenterHorizontally).padding(16.dp)
            ) {
               Text(
                   text="My Pokemon List",
                   style= MaterialTheme.typography.button,
                   textAlign = TextAlign.Center
               )
            }
        }
    }
    @Composable
    fun PokemonEntry(
        entry: PokemonListEntry,
        navController: NavController,
        modifier: Modifier = Modifier,
        viewModel: PokemonListViewModel = hiltViewModel()
    ) {
        val defaultDominantColor = MaterialTheme.colors.surface
        var dominantColor by remember {
            mutableStateOf(defaultDominantColor)
        }

        Box(
            contentAlignment = Center,
            modifier = modifier
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .aspectRatio(1f)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            dominantColor,
                            defaultDominantColor
                        )
                    )
                )
                .clickable {
                    navController.navigate(
                        "pokemon_detail_screen/${dominantColor.toArgb()}/${entry.pokemonName}"
                    )
                }
        ) {
            Column {
                CoilImage(
                    request = ImageRequest.Builder(LocalContext.current)
                        .data(entry.imageUrl)
                        .target {
                            viewModel.calcDominantColor(it) { color ->
                                dominantColor = color
                            }
                        }
                        .build(),
                    contentDescription = entry.pokemonName,
                    fadeIn = true,
                    modifier = Modifier
                        .size(120.dp)
                        .align(CenterHorizontally)
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.scale(0.5f)
                    )
                }
                Text(
                    text = entry.pokemonName,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    @Composable
    fun PokedexRow(
        rowIndex: Int,
        entries: List<PokemonListEntry>,
        navController: NavController
    ) {
        Column {
            Row {
                PokemonEntry(
                    entry = entries[rowIndex * 2],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                if (entries.size >= rowIndex * 2 + 2) {
                    PokemonEntry(
                        entry = entries[rowIndex * 2 + 1],
                        navController = navController,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

























