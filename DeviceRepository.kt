package com.rbinternational.retail.mobileapp.common.devices

import com.rbinternational.retail.mobileapp.common.devices.model.Device
import com.rbinternational.retail.mobileapp.common.devices.model.DeviceType
import kotlinx.coroutines.delay

class DeviceRepository {

    suspend fun loadDevices(): List<Device> {
        delay(2000)
        return listOf(
            Device("1", "iPhone X", DeviceType.IOS),
            Device("2", "Samsung Galaxy A52", DeviceType.ANDROID),
            Device("3", "Google Pixel 5", DeviceType.ANDROID),
            Device("4", "OAZ Fridge", DeviceType.UNKNOWN),
            Device("5", "OnePlus 9", DeviceType.ANDROID),
            Device("6", "iPhone 11", DeviceType.IOS),
            Device("7", "Blackberry", DeviceType.BLACKBERRY),
            Device("8", "iPhone SE", DeviceType.IOS),
            Device("9", "Xiaomi Mi 11", DeviceType.ANDROID),
            Device("10", "iPhone 13", DeviceType.IOS),
            Device("11", "Samsung Galaxy Note 20", DeviceType.ANDROID),
            Device("12", "iPhone 8", DeviceType.IOS),
            Device("13", "Google Pixel 4a", DeviceType.ANDROID),
            Device("14", "iPhone XR", DeviceType.IOS),
            Device("15", "OnePlus 8T", DeviceType.ANDROID),
            Device("16", "iPhone 7", DeviceType.IOS),
            Device("17", "Samsung Galaxy S10", DeviceType.ANDROID),
            Device("18", "iPhone 6", DeviceType.IOS),
            Device("19", "Google Pixel 3", DeviceType.ANDROID),
            Device("20", "iPhone 5", DeviceType.IOS)
        )
    }

    suspend fun deleteDevices(devices: List<Device>) {
        devices.toSet()
        delay(500)
    }
}
