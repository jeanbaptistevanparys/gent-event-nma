package com.example.gentevent.ui.screens.settings

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gentevent.ui.screens.components.RoundedContainer
import com.example.gentevent.ui.screens.components.Top


@Composable
fun SettingsScreen(navController: NavHostController?) {
    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        topBar = {
            Top {
                navController?.popBackStack()
            }
        },
        content = { innerPadding ->
            RoundedContainer(
                innerPadding = innerPadding,
                content = {
                    SettingsContainer()
                }
            )
        }
    )
}

@Composable
fun SettingsContainer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Setting(title = "Notifications") { NotificationPermission() }
    }
}

@Composable
fun Setting(title: String, content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = title,
            style = MaterialTheme.typography.h6
        )
        content()
    }
}

@Composable
fun NotificationPermission() {
    val context = LocalContext.current

    val checked = remember { mutableStateOf(checkPermission(context)) }

    val intent = Intent()
    intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
    intent.putExtra("android.provider.extra.APP_PACKAGE", context.packageName)

    val notificationSettingsLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            checked.value = checkPermission(context)
    }

    Switch(
        checked = checked.value,
        onCheckedChange = {
            notificationSettingsLauncher.launch(intent)
        },
        modifier = Modifier.padding(10.dp),
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colors.primary,
            checkedTrackColor = MaterialTheme.colors.primary,
            uncheckedThumbColor = Color.Gray,
            uncheckedTrackColor = Color.LightGray,
        )
    )
}

fun checkPermission(context: Context): Boolean {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    return notificationManager.areNotificationsEnabled()
}
