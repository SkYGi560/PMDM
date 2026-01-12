package com.pmdm.scaffold.features.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TabScreen(){
    Scaffold(
        topBar = { TopAppBarJoel() },
        bottomBar = { NavigationBarJoel() }
    ) { paddingValues ->
        data class MenuItem(val title: String, val iconRes: Int)

        val menuItems = listOf(
            MenuItem("Ingresos", com.pmdm.scaffold.R.drawable.paid_24px),
            MenuItem("Facturas", com.pmdm.scaffold.R.drawable.receipt_long_24px),
            MenuItem("Pendientes", com.pmdm.scaffold.R.drawable.pending_actions_24px),
            MenuItem("Balance", com.pmdm.scaffold.R.drawable.balance_24px),
        )
        Surface(modifier = Modifier.padding(paddingValues).fillMaxHeight()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(menuItems) { item ->
                    ElevatedCard(
                        onClick = { },
                        modifier = Modifier.padding(8.dp)
                            .height(330.dp)
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(item.iconRes),
                                contentDescription = item.title
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(item.title)
                        }
                    }
                }
            }
        }
    }
}