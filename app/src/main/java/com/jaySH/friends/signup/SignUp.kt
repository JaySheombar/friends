package com.jaySH.friends.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaySH.friends.R

@Preview(device = Devices.PIXEL_4)
@Composable
fun SignUp() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ScreenTitle(title = R.string.create_account)

        EmailField(email = email, onValueChange = { email = it })

        PasswordField(password = password, onValueChange = { password = it })

        SignUpButton(onClick = {})
    }
}

@Composable
private fun SignUpButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) = Button(
    modifier = modifier
        .fillMaxWidth()
        .testTag(stringResource(id = R.string.sign_up)),
    onClick = onClick,
    content = { Text(text = stringResource(id = R.string.sign_up)) },
)

@Composable
private fun ScreenTitle(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
) = Text(
    modifier = modifier.fillMaxWidth(),
    text = stringResource(id = title),
    style = typography.h4,
)

@Composable
private fun EmailField(
    modifier: Modifier = Modifier,
    email: String,
    onValueChange: (String) -> Unit,
) = OutlinedTextField(
    modifier = modifier
        .fillMaxWidth()
        .testTag(stringResource(id = R.string.email)),
    value = email,
    label = { Text(text = stringResource(id = R.string.email)) },
    onValueChange = onValueChange,
)

@Composable
private fun PasswordField(
    modifier: Modifier = Modifier,
    password: String,
    onValueChange: (String) -> Unit,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .testTag(stringResource(id = R.string.password)),
        value = password,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.password)) },
        trailingIcon = {
            IconButton(
                content = { VisibilityIcon() },
                onClick = { passwordVisible = !passwordVisible },
            )
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
    )
}

@Composable
private fun VisibilityIcon() = Icon(
    painter = painterResource(id = R.drawable.ic_visibility),
    contentDescription = stringResource(id = R.string.toggle_visibility),
)