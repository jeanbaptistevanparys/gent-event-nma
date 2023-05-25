package com.example.gentevent.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gentevent.ui.screens.components.RoundedContainer
import com.example.gentevent.ui.screens.components.Top
import com.example.gentevent.ui.theme.GenteventTheme


@Composable
fun SettingsScreen(navController: NavHostController?) {
    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        topBar = { Top {
            navController?.popBackStack()
        }
        },
        content = { innerPadding ->
            RoundedContainer(
                innerPadding = innerPadding,
                content = {
                    Settings()
                }
            )
        }
    )
}

@Composable
fun Settings() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Setting(text = "setting 1")
        Setting(text = "setting 2")
        Setting(text = "setting 3")
    }
}

@Composable
fun Setting(text: String) {
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
            text = text,
            style = MaterialTheme.typography.h6
        )
        Switch(
            checked = true,
            enabled = false,
            onCheckedChange = { isChecked ->
                if (isChecked) {
                    // The switch is checked
                } else {
                    // The switch is not checked.
                }
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
}

@Preview
@Composable
fun SettingsPreview() {
    GenteventTheme {
        SettingsScreen(null)
    }
}