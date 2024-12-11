package com.rbinternational.retail.mobileapp.common.devices

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rbinternational.mobrix.design.bars.BarAction
import com.rbinternational.mobrix.design.bars.BarTitle
import com.rbinternational.mobrix.design.bars.NavigationBarUDS
import com.rbinternational.mobrix.design.bars.backArrowAppBarAction
import com.rbinternational.mobrix.design.button.ButtonStackUDS
import com.rbinternational.mobrix.design.button.ButtonStackUDSContent
import com.rbinternational.mobrix.design.button.ButtonStyle
import com.rbinternational.mobrix.design.layout.ScaffoldFooter
import com.rbinternational.mobrix.design.layout.ScaffoldUDS
import com.rbinternational.mobrix.design.theme.UnifiedDesignSystemTheme
import com.rbinternational.mobrix.design.theme.UnifiedDesignSystemTheme.colors
import com.rbinternational.mobrix.design.theme.UnifiedDesignSystemTheme.spaces
import com.rbinternational.retail.mobileapp.common.R
import com.rbinternational.retail.mobileapp.common.compose.components.FullScreenLoading
import com.rbinternational.retail.mobileapp.common.devices.components.DeviceListItem
import com.rbinternational.retail.mobileapp.common.devices.model.Device
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoggedInDevicesScreen(
    onBack: () -> Unit
) {
    val viewModel = koinViewModel<LoggedInDevicesViewModel>()
    val isLoading = viewModel.isLoading.collectAsState()

    var devices = emptyList<Device>()
    val selectedDevices = mutableListOf<Device>()

    rememberCoroutineScope().launch {
        devices = viewModel.loadDevices()
    }

    var isDeletionActive by remember { mutableStateOf(false) }

    ScaffoldUDS(
        scrollable = true,
        topBar = NavigationBarUDS(
            titleBar = BarTitle.TextBarTitle(text = "Select devices to delete"),
            leftAction = backArrowAppBarAction { onBack() },
            rightAction = BarAction.IconBarAction(
                image = if (isDeletionActive) {
                    R.drawable.ui_ic_close
                } else {
                    R.drawable.ui_ic_trash
                },
                onClick = { isDeletionActive = !isDeletionActive },
                contentDescription = null
            )
        ),
        footer = ScaffoldFooter.BaseFooterUDS {
            if (isDeletionActive && !isLoading.value) {
                ButtonStackUDS(
                    primary = ButtonStackUDSContent(
                        text = stringResource(R.string.delete),
                        onClick = { viewModel.deleteDevices(selectedDevices) },
                        style = ButtonStyle.Danger
                    )
                )
            }
        }
    ) {
        if (isLoading.value) {
            FullScreenLoading()
        } else {
            Column {
                devices.forEach { device ->
                    DeviceListItem(
                        paddingLeft = spaces.padding_2_0,
                        paddingRight = spaces.padding_2_0,
                        device = device,
                        isCheckingAvailable = isDeletionActive,
                        onCheckboxClicked = { isChecked ->
                            if (isChecked) {
                                selectedDevices.add(device)
                            }
                        }
                    )
                    HorizontalDivider(color = colors.shadowPrimary)
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoggedInDevicesScreenPreview() {
    UnifiedDesignSystemTheme {
        LoggedInDevicesScreen(
            onBack = {}
        )
    }
}
