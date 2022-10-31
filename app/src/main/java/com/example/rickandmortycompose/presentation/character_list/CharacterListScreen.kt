package com.example.rickandmortycompose.presentation.character_list

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter


@Composable
fun CharacterListScreen(characterListViewModel: CharacterListViewModel = hiltViewModel()) {

    val character = characterListViewModel.state.value.character.collectAsLazyPagingItems()
    val context = LocalContext.current

    LazyColumn() {
        items(character) { item ->
            Column {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures {
                            Toast.makeText(context, "${item?.name}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                    Image(
                        painter = rememberImagePainter(data = item?.image),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                    Text(item?.name ?: "", modifier = Modifier.padding(5.dp))
                }
                Divider()
            }
        }
    }
}