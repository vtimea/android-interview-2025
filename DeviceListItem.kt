package com.rbinternational.retail.mobileapp.common.devices.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.rbinternational.mobrix.design.controls.CheckboxUDS
import com.rbinternational.mobrix.design.theme.UnifiedDesignSystemTheme.spaces
import com.rbinternational.mobrix.design.theme.UnifiedDesignSystemTheme.typography
import com.rbinternational.retail.mobileapp.common.R
import com.rbinternational.retail.mobileapp.common.devices.model.Device
import com.rbinternational.retail.mobileapp.common.devices.model.DeviceType

@Composable
fun DeviceListItem(
    paddingLeft: Dp,
    paddingRight: Dp,
    device: Device,
    isCheckingAvailable: Boolean,
    onCheckboxClicked: (Boolean) -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spaces.padding_1_0),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(spaces.padding_2_0),
            painter = painterResource(
                if (device.type == DeviceType.ANDROID) {
                    R.drawable.ui_ic_google
                } else if (device.type == DeviceType.IOS) {
                    R.drawable.ui_ic_apple_inc
                } else if (device.type == DeviceType.BLACKBERRY) {
                    R.drawable.ui_ic_bear
                } else {
                    R.drawable.ui_ic_warning_exclamation
                }
            ),
            contentDescription = null
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.padding(start = paddingLeft, end = paddingRight),
                text = device.name,
                style = typography.body1
            )
        }
        if (isCheckingAvailable) {
            CheckboxUDS(
                modifier = Modifier.padding(spaces.padding_2_0),
                checked = isChecked,
                onCheckedChange = { newIsChecked ->
                    isChecked = newIsChecked
                    onCheckboxClicked(newIsChecked)
                }
            )
        }
    }
}
