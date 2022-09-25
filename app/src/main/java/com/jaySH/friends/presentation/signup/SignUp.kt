package com.jaySH.friends.presentation.signup

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jaySH.friends.R

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SignUp(
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignedUp: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }

    if (state.signUpState is SignUpState.SignedUp) onSignedUp()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ScreenTitle(title = R.string.create_account)

        EmailField(email = email, onValueChange = { email = it })

        PasswordField(password = password, onValueChange = { password = it })

        AboutField(value = about, onValueChange = { about = it })

        SignUpButton(onClick = { viewModel.createAccount(email, password, about) })
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
                content = { VisibilityIcon(passwordVisible) },
                onClick = { passwordVisible = !passwordVisible },
            )
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
    )
}

@Composable
private fun VisibilityIcon(visible: Boolean) = Icon(
    painter = if (visible) painterResource(id = R.drawable.ic_invisible)
    else painterResource(id = R.drawable.ic_visible),
    contentDescription = stringResource(id = R.string.toggle_visibility),
)

@Composable
fun AboutField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) = OutlinedTextField(
    modifier = modifier
        .fillMaxWidth()
        .testTag(stringResource(id = R.string.about)),
    value = value,
    label = { Text(text = stringResource(id = R.string.about)) },
    onValueChange = onValueChange,
)