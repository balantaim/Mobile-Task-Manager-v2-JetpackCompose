package com.baubuddy.v2.view.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.baubuddy.v2.R
import com.baubuddy.v2.navigation.AppScreens
import com.baubuddy.v2.viewmodel.SearchViewModel
import com.baubuddy.v2.widgets.CustomTopAppBar
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {

//    val searchText by viewModel.searchText.collectAsState()
//    val persons by viewModel.persons.collectAsState()
//    val isSearching by viewModel.isSearching.collectAsState()

    Scaffold(topBar = {
        CustomTopAppBar(title = "Search data",
            icon = Icons.Default.ArrowBack,
            false,
            navController = navController,
            onAddActionClicked = {},
            onButtonClicked = { navController.popBackStack() })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            //Text(text = "Search")
            MySearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Start),
                viewModel) { indexTask ->
                navController.navigate(AppScreens.HomeScreen.name + "/$indexTask")
            }
            //QR functions
            Text(text = "")
            Button(onClick = { scanInit() }) {
                Text(text = stringResource(R.string.scan_barcode))

            }
        }
    }
}

fun scanInit(){
//        val options = GmsBarcodeScannerOptions.Builder()
//        .setBarcodeFormats(
//            Barcode.FORMAT_QR_CODE,
//            Barcode.FORMAT_AZTEC)
//        .enableAutoZoom()
//        .build()
//
//    val context = LocalContext.current
//    val scanner = GmsBarcodeScanning.getClient(context, options)
//    scanner.startScan()
//        .addOnSuccessListener { barcode ->
//            // Task completed successfully
//            Log.d("QR", "QR: " + barcode.rawValue)
//        }
//        .addOnCanceledListener {
//            // Task canceled
//        }
//        .addOnFailureListener { e ->
//            // Task failed with an exception
//        }
}

    @ExperimentalComposeUiApi
    @Composable
    fun MySearchBar(
        modifier: Modifier = Modifier,
        viewModel: SearchViewModel,
        onSearch: (String) -> Unit = {viewModel.searchEvent()}
    ) {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()
        }

        Column {
            CustomSearchBar(
                valueState = searchQueryState,
                placeholder = "Seattle",
                onAction = KeyboardActions {
                    if (!valid) return@KeyboardActions
                    onSearch(searchQueryState.value.trim())
                    searchQueryState.value = ""
                    keyboardController?.hide()
                })

        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomSearchBar(
        valueState: MutableState<String>,
        placeholder: String,
        keyboardType: KeyboardType = KeyboardType.Text,
        imeAction: ImeAction = ImeAction.Next,
        onAction: KeyboardActions = KeyboardActions.Default
    ) {
        OutlinedTextField(
            value = valueState.value,
            onValueChange = { valueState.value = it },
            label = { Text(text = placeholder) },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = onAction,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = androidx.compose.ui.graphics.Color.Blue,
                cursorColor = androidx.compose.ui.graphics.Color.Black
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
    }

