package com.rbinternational.retail.mobileapp.common.devices

import com.rbinternational.retail.mobileapp.common.BaseViewModel
import com.rbinternational.retail.mobileapp.common.devices.model.Device
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class LoggedInDevicesViewModel(
    private val deviceRepository: DeviceRepository
) : BaseViewModel() {

    private var devices = emptyList<Device>()

    val isLoading = MutableStateFlow(true)

    suspend fun loadDevices(): List<Device> =
        withContext(Dispatchers.IO) {
            isLoading.value = true

            val loadedDevices = deviceRepository.loadDevices()
            devices = loadedDevices

            isLoading.value = false

            return@withContext loadedDevices
        }

    fun deleteDevices(devicesToDelete: List<Device>) {
        isLoading.value = true

        runBlocking {
            launch(Dispatchers.IO) {
                deviceRepository.deleteDevices(devicesToDelete)
                loadDevices()
            }
        }

        isLoading.value = false
    }
}
