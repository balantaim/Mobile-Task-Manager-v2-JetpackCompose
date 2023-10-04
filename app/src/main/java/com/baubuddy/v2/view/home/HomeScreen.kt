package com.baubuddy.v2.view.home

import android.annotation.SuppressLint
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.baubuddy.v2.R
import com.baubuddy.v2.model.Task
import com.baubuddy.v2.navigation.AppScreens
import com.baubuddy.v2.tools.WindowInfo
import com.baubuddy.v2.tools.rememberWindowSize
import com.baubuddy.v2.ui.theme.BauBuddyTheme
import com.baubuddy.v2.viewmodel.MainViewModel
import com.baubuddy.v2.widgets.CustomTopAppBar

@Composable
fun HomeScreen (navController: NavController, viewModel: MainViewModel = hiltViewModel()){
    //Get the screen size
    val windowsInfo = rememberWindowSize()
    //Get the data from the DB
    val taskList = viewModel.taskList.collectAsState().value
    Column {
        Row (modifier = Modifier.fillMaxWidth(1f)){
            if(taskList.isNotEmpty()){
                MainScaffold(taskList, windowsInfo,navController)
                //MessageList(taskList, windowsInfo, navController)
            }else{
//                Box (modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center){
//                    Text(text = "No data!")
//                }
            }
        }
    }
}

@Composable
fun MessageList(messages: List<Task>, windowsInfo: WindowInfo, navController: NavController) {
    LazyColumn{
        items(messages){ index ->
            Card(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
                .height(200.dp),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(2.dp, colorValidator(index))
            ) {
                Column (modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Title: ${index.title}",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 10.dp))
                    //Text(text = "Id: ${index.id}")
                    Text(text = "Task: ${index.description}")
                }
                if (windowsInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
                    Column(modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight(1f)
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Bottom) {
                        Button(onClick = {
                                         getDetails(index.id, navController)
                                         },
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(0.5f)) {
                            Text(text = stringResource(R.string.details))
                        }
                    }
                } else {
                    Column(modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight(1f)
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom) {
                        Button(onClick = {
                            getDetails(index.id, navController)
                        },
                            modifier = Modifier
                                .padding(4.dp)
                                .width(150.dp)) {
                            Text(text = stringResource(R.string.details))
                        }
                    }
                }
            }
            //Spacer(modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScaffold(taskList: List<Task>, windowsInfo: WindowInfo, navController: NavController) {
    Scaffold(topBar = {
        CustomTopAppBar(title = "My title",
            null,
            true,
            navController = navController,
            onAddActionClicked = {
                navController.navigate(AppScreens.SearchScreen.name)
            },
            onButtonClicked = {})
    })
    {innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)){
            MessageList(taskList, windowsInfo, navController)
        }
    }
}

private fun getDetails(taskID: String, navController: NavController){
    navController.navigate(AppScreens.DetailsScreen.name + "/$taskID")
}

@Composable
private fun colorValidator(index: Task): Color{
    if(index.colorCode.isBlank() || index.colorCode.equals("")) {
        if(isSystemInDarkTheme()){
        return Color.Black
        }
        return Color.White
    }
    val color = Color(index.colorCode.toColorInt())
    return color
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BauBuddyTheme {

    }
}
