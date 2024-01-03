package com.example.portflioapp.ui.views

import android.icu.text.CaseMap.Title
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portflioapp.R
import com.example.portflioapp.data.Research
import com.example.portflioapp.data.SampleData
import com.example.portflioapp.ui.ScaffoldTemplateAppBar
import kotlin.reflect.KProperty

@Composable
fun ResearchPage(
    onSideButtonClick: () -> Unit = {},
){
    ScaffoldTemplateAppBar(
        title = "Research",
        onSideButtonClick = onSideButtonClick
    ) {
        ResearchPageElements(
            researchList = SampleData.research,
            paddingValues = it
        )
    }
}


@Composable
fun ResearchPageElements(
    researchList: List<Research>,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ){
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                ){
                    Text(
                        text = "Title",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .weight(1f)
                    )
                    Text(
                        text = "Citations",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .weight(0.3f),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Year",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .weight(0.3f),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
        items(
            count = researchList.size
        ){
            ResearchCard(research = researchList[it])
        }
    }
}

@Composable
fun ResearchCard(
    research: Research
){
    var color = MaterialTheme.colorScheme.tertiaryContainer
    var textColor = MaterialTheme.colorScheme.onTertiaryContainer
    var maxLines by rememberSaveable{
        mutableIntStateOf(3)
    }

    if (maxLines==3){
        color = MaterialTheme.colorScheme.secondaryContainer
        textColor = MaterialTheme.colorScheme.onSecondaryContainer
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize()
            .clickable {
                maxLines = if (maxLines == 6) {
                    3
                } else {
                    6
                }
            }
        ,
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight(),
            ) {
                Text(
                    text = research.title,
                    maxLines = maxLines,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(0.dp,0.dp,0.dp,5.dp),
                    color = textColor
                )
                Text(
                    text = research.author,
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor
                )
                Text(
                    text = research.published,
                    style = MaterialTheme.typography.labelMedium,
                    color = textColor
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .wrapContentHeight(),
                text = research.citation.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                color = textColor
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .wrapContentHeight(),
                text = research.year.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                color = textColor
            )
        }
    }
}

//@Preview
//@Composable
//fun PreviewResearchCard(){
//    ResearchCard(research = Research())
//}
//
//@Preview
//@Composable
//fun PreviewResearchPage(){
//    ResearchPage()
//}