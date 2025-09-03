package com.example678.unitconverterapp.viewmodel



import androidx.lifecycle.ViewModel
import com.example678.unitconverterapp.data.ConversionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UnitConverterViewModel(
    private val repository: ConversionRepository = ConversionRepository()
) : ViewModel() {

    private val _inputValue = MutableStateFlow("")
    val inputValue: StateFlow<String> = _inputValue

    private val _fromUnit = MutableStateFlow("cm")
    val fromUnit: StateFlow<String> = _fromUnit

    private val _toUnit = MutableStateFlow("m")
    val toUnit: StateFlow<String> = _toUnit

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result

    fun onValueChange(newValue: String) {
        _inputValue.value = newValue
    }

    fun onFromUnitChange(unit: String) {
        _fromUnit.value = unit
    }

    fun onToUnitChange(unit: String) {
        _toUnit.value = unit
    }

    fun convert() {
        val value = _inputValue.value.toDoubleOrNull()
        if (value != null) {
            val converted = repository.convert(value, _fromUnit.value, _toUnit.value)
            _result.value = converted.toString()
        } else {
            _result.value = "Invalid input"
        }
    }
}
