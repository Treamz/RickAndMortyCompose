package com.example.rickandmortycompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.rickandmortycompose.data.RetrofitInst
import com.example.rickandmortycompose.data.repository.BaseRepository
import com.example.rickandmortycompose.ui.theme.RickAndMortyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTheme {
                val characterApi = RetrofitInst.apiCharacter
                val baseRepository = BaseRepository(characterApi)
                val mainViewModelFactory = MainViewModelFactory(baseRepository)
                val mainViewModel =
                    ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

                val character = mainViewModel.character.collectAsLazyPagingItems()

                LazyColumn() {
                    items(character) { item ->
                        Column {
                            Row(horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.pointerInput(Unit) {
                                    detectTapGestures {
                                        Toast.makeText(this@MainActivity, "${item?.name}", Toast.LENGTH_SHORT).show()
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
        }
    }
}

