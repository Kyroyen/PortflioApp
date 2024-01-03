package com.example.portflioapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portflioapp.R
import com.example.portflioapp.ui.ScaffoldTemplateAppBar

@Composable
fun AboutPageElements(
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ){
        ElevatedCard(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Text(
                text = stringResource(id = R.string.about),
                modifier = Modifier
                    .padding(16.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 15.sp,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
fun AboutPage(
    onSideButtonClick: () -> Unit = {},
){
    ScaffoldTemplateAppBar(
        title = "About",
        elements = {
            AboutPageElements(it)
        },
        onSideButtonClick = {
            onSideButtonClick()
        }
    )
}

//@Preview
//@Composable
//fun PreviewAboutPage(){
//    AboutPage()
//}