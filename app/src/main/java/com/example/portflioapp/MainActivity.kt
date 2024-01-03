package com.example.portflioapp

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portflioapp.data.NavigationItem
import com.example.portflioapp.ui.theme.PortflioAppTheme
import com.example.portflioapp.ui.views.AboutPage
import com.example.portflioapp.ui.views.LandingPage
import com.example.portflioapp.ui.views.ResearchPage
import com.example.portflioapp.ui.views.openLink
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortflioAppTheme {
                val menuItems = listOf(
                    "Home",
                    "Research",
                    "Recommendations",
                    "About",
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    var selectedItemIndex by rememberSaveable {
                        mutableStateOf(0)
                    }
                    ModalNavigationDrawer(
                        drawerContent = {
                            ModalDrawerSheet {
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Spacer(
                                            modifier = Modifier
                                                .height(16.dp)
                                        )
                                        menuItems.forEachIndexed { index, item ->
                                            NavigationDrawerItem(
                                                label = { Text(text = item) },
                                                selected = selectedItemIndex == index,
                                                onClick = {
                                                    //TODO
                                                    selectedItemIndex = index
                                                    scope.launch {
                                                        drawerState.close()
                                                    }
                                                },
                                                modifier = Modifier
                                                    .width(250.dp)
                                                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                        }
                                    }
                                    Row(
                                        modifier = Modifier
                                            .padding(16.dp)
                                    ) {
                                        IconButton(onClick = {
                                            applicationContext.openLink(
                                                applicationContext.getString(R.string.your_linkedin)
                                            )
                                        }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.linkedin),
                                                contentDescription = "LinkedIn"
                                            )
                                        }
                                        Spacer(
                                            modifier = Modifier
                                                .width(10.dp)
                                        )
                                        IconButton(onClick = {
                                            applicationContext.openLink(
                                                applicationContext.getString(R.string.your_google_scholar)
                                            )
                                        }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.google_scholar),
                                                contentDescription = "Google Scholar"
                                            )
                                        }
                                    }
                                }
                            }
                        },
                        drawerState = drawerState
                    ) {
                        ResearchPage {
                            scope.launch {
                                Log.d("Bhosda", "Open")
                                drawerState.open()
                            }
                        }
                    }
                }
            }
        }
    }
}