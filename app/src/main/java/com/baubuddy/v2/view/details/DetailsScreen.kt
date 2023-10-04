package com.baubuddy.v2.view.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.baubuddy.v2.tools.rememberWindowSize
import com.baubuddy.v2.viewmodel.DetailsViewModel

@Composable
fun DetailsScreen (navController: NavController, viewModel: DetailsViewModel = hiltViewModel(), id: String?){
    val itemID = id
    arrangeInfo(navController, viewModel, id)
}

@Composable
private fun arrangeInfo(controller: NavController, viewModel: DetailsViewModel, id: String?) {
    //Get the screen size
    val windowsInfo = rememberWindowSize()
    //val info = id?.let { viewModel.getInfo(it) }
    Column (modifier = Modifier
        .padding(4.dp)
        .fillMaxSize()){
        //Text(text = "Title")
        Row (modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly){
            Text(text = "ID: $id")



//            Text(text = "Title: ${info!!.title}")
//            Text(text = "Description: ${info!!.description}")
//            Button(onClick = {
//                             
//            },
//                modifier = Modifier.width(150.dp)
//            ) {
//                Text(text = stringResource(R.string.delete))
//            }
//            Button(onClick = {
//
//            },
//                modifier = Modifier.width(150.dp)
//            ) {
//                Text(text = stringResource(R.string.save))
//            }
        }

    }
}