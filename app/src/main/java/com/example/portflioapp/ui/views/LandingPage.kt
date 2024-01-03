package com.example.portflioapp.ui.views

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portflioapp.R
import com.example.portflioapp.data.Experience
import com.example.portflioapp.data.SampleData

fun Context.sendMail(to: String) {
    try {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$to")
        }
        startActivity(Intent.createChooser(emailIntent, "Send feedback"))
    } catch (e: ActivityNotFoundException) {
        // TODO: Handle case where no email app is available
    } catch (t: Throwable) {
        // TODO: Handle potential other type of exceptions
    }
}

fun Context.openLink(url:String){
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(browserIntent)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage(
    onSideButtonClick: () -> Unit = {},
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "NiggerMan",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            Log.d("Bhosda", "BackOpen")
                            onSideButtonClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = {
                        context.sendMail(
                            to = context.getString(R.string.your_mail)
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            ElevatedCard(
                modifier = Modifier
                    .padding(12.dp, 12.dp, 12.dp, 0.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.your_picture),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(16.dp, 16.dp, 16.dp, 0.dp)
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Text(
                    text = stringResource(id = R.string.your_name),
                    modifier = Modifier
                        .padding(16.dp, 16.dp, 16.dp, 0.dp)
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp,
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = stringResource(id = R.string.work),
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {

                Column{
                    Text(
                        text = "Experience",
                        modifier = Modifier
                            .padding(16.dp, 16.dp, 16.dp, 0.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 32.sp,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    SampleData.experience.forEach {
                        ExperienceCard(exp = it)
                    }
                    Spacer(modifier  = Modifier.height(8.dp))
                }
            }

        }
    }

}

@Composable
fun ExperienceCard(exp: Experience) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp, 8.dp, 8.dp, 0.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        )
    ) {
        Text(
            text = exp.title,
            modifier = Modifier
                .padding(16.dp, 8.dp, 16.dp, 0.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.displayLarge,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = exp.organization,
            modifier = Modifier
                .padding(16.dp, 8.dp, 16.dp, 0.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.displayMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = exp.location,
            modifier = Modifier
                .padding(16.dp, 8.dp, 16.dp, 0.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.displaySmall,
            fontSize = 13.sp,
        )
        Text(
            text = exp.fromTo,
            modifier = Modifier
                .padding(16.dp, 8.dp, 16.dp, 8.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.displaySmall,
            fontSize = 10.sp,
            fontWeight = FontWeight.Black
        )
    }
}


//@Preview
//@Composable
//fun PreviewExperienceCard() {
//    ExperienceCard(exp = Experience())
//}
//
//@Preview
//@Composable
//fun PreviewLandingPage() {
//    LandingPage()
//}