package com.jaySH.friends.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jaySH.friends.R

@Preview(device = Devices.PIXEL_4)
@Composable
fun SignUp() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Create an account")

        OutlinedTextField(
            value = email,
            label = { Text(text = stringResource(id = R.string.email)) },
            onValueChange = { email = it },
        )

        OutlinedTextField(
            value = password,
            label = { Text(text = stringResource(id = R.string.password)) },
            onValueChange = { password = it },
        )

        Button(
            onClick = {},
            content = { Text(text = stringResource(id = R.string.sign_up)) },
        )
    }
}