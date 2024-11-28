package com.conelab.moviesexam.ui.common_composables

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.conelab.moviesexam.R
import com.conelab.moviesexam.data.model.MovieDetailsItem
import com.conelab.moviesexam.data.model.MovieItem
import com.conelab.moviesexam.ui.theme.AppColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
) {
    TopAppBar(

        title = {
            Text(
                text = title, color = Color.White
            )
        },
        actions = {
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF5D74A6), // Color de fondo

        )


    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.left_arrow),
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF5D74A6),
        )
    )
}


@Composable
fun MovieCard(index: Int, movie: MovieItem, onClick: () -> Unit) {
    val imageUrl = "https://image.tmdb.org/t/p/w500${movie.backdrop_path}"


    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        colors =
        CardDefaults.cardColors(
            containerColor = AppColors.cardBackgroundColor,
            contentColor = Color.Black
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = index.toString())

                Text(
                    text = movie.title,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.titleLarge

                )
            }

            AsyncImage(
                model = imageUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}

@Composable
fun MovieDetailsCard(movieDetails: MovieDetailsItem) {

    val imageUrl = "https://image.tmdb.org/t/p/w500${movieDetails.backdrop_path}"
    val genresString = movieDetails.genres.joinToString(", ") { it.name }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        colors =
        CardDefaults.cardColors(
            containerColor = AppColors.cardBackgroundColor,
            contentColor = Color.Black
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = movieDetails.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Título: " + movieDetails.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Genero: " + genresString,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Duración: " + movieDetails.runtime + " minutos",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Descripción:\n\n" + movieDetails.overview,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 12,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Composable
fun ErrorDialog() {
    val context = LocalContext.current
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Error") },
        text = { Text(text = "No hay conexión a Internet o el ID de la película no es válido.") },
        confirmButton = {
            Button(onClick = { (context as? Activity)?.finish() }) {
                Text("Salir")
            }
        }
    )
}

@Composable
fun SquareButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(100.dp)
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF5D74A6),
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp)
        )
    }
}