package com.example.mykmmmovieapp.android.ui.movieList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.mykmmmovieapp.domain.entity.MovieItem

@Composable
fun MovieItem(
    item: MovieItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = Modifier.weight(1f),
                elevation = 10.dp
            ) {
                SubcomposeAsyncImage(
                    model = item.thumb,
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator(Modifier.width(5.dp).height(5.dp))
                    },
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                modifier = Modifier
                    .weight(3f)
                    .padding(8.dp)
            ) {
                Text(text = item.title, style =MaterialTheme.typography.h5, maxLines = 1)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.subTitle.toString())
            }

        }
    }
}