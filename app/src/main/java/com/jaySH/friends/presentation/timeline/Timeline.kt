package com.jaySH.friends.presentation.timeline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.jaySH.friends.R

@Composable
fun Timeline() = Column(
    modifier = Modifier
        .fillMaxSize()
        .testTag(stringResource(id = R.string.timeline)),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
) {
    Text(
//        modifier = Modifier.testTag(tag = stringResource(id = R.string.timeline)),
        text = stringResource(id = R.string.timeline),
    )
}