package com.example.component.presentation.ui.basic.component3.chap08

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.component.presentation.ui.basic.component3.chap08.viewmodel.PokemonViewModel

@Composable
fun DetailScreen(
    pokemonId: Int,
    onUpButtonClick: () -> Unit,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    viewModel.getPokemon(pokemonId)

    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            val result = viewModel.pokemonResult
            val pokemonName = result.species.name

            Text(pokemonName)

            AsyncImage(
                model = result.sprites.frontDefault,
                contentDescription = pokemonName,
                modifier = Modifier.size(100.dp)
            )

            Button(onClick = onUpButtonClick) {
                Text("위로")
            }
        }
    }
}

// 단계 1: viewModel을 제대로 설정하자. `hiltViewModel()`를 사용한다.
@Composable
fun MainScreen(
    onPokemonClick: (String) -> Unit,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    val response = viewModel.pokemonList.collectAsLazyPagingItems()
    LazyColumn {
        items(
            response.itemCount,
            key = { index ->
                response[index]?.url ?: index
            } // key는 nullable 대응
        ) { index ->
            val item = response[index]
            item?.let {
                Card(
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column {
                            Text("포케몬: ${it.name}")
                            Text(
                                text = it.url,
                                Modifier.alpha(0.4f)
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Button(
                            onClick = {
                                onPokemonClick(it.url)
                            }
                        ) {
                            Text("보기")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopLevel(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        "Home",
        modifier = modifier
    ) {
        composable("Home") {
            MainScreen(
                onPokemonClick = {
                    val pokemonId = it.substringAfter("pokemon/")
                        .substringBefore("/")
                        .toInt()
                    navController.navigate("Detail/${pokemonId}")
                }
            )
        }

        // 단계 3: arguments 파라미터를 설정하자.
        // ```
        // navArgument("pokemenId") {
        // type = NavType.IntType
        // }
        // ```
        // 리스트로 전달해야 한다.
        composable(
            "Detail/{pokemonId}",
        ) {
            // 단계 4: `pokemonId`를 `Int`값으로 가져오자. (`arguments?.getInt`를 이용)
            val pokemonId = 0
            DetailScreen(
                pokemonId = pokemonId,
                onUpButtonClick = {
                    navController.navigate("Home") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}