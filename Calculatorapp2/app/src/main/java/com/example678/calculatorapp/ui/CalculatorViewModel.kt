package com.example678.calculatorapp.ui



import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example678.calculatorapp.data.CalculatorRepository

class CalculatorViewModel : ViewModel() {

    private val repository = CalculatorRepository()

    private val _input1 = MutableStateFlow("")
    val input1: StateFlow<String> = _input1

    private val _input2 = MutableStateFlow("")
    val input2: StateFlow<String> = _input2

    private val _operator = MutableStateFlow("+")
    val operator: StateFlow<String> = _operator

    private val _result = MutableStateFlow("0")
    val result: StateFlow<String> = _result

    fun onInput1Changed(value: String) {
        _input1.value = value
    }

    fun onInput2Changed(value: String) {
        _input2.value = value
    }

    fun onOperatorChanged(value: String) {
        _operator.value = value
    }

    fun calculate() {
        val num1 = _input1.value.toDoubleOrNull() ?: 0.0
        val num2 = _input2.value.toDoubleOrNull() ?: 0.0
        val res = repository.calculate(num1, num2, _operator.value)
        _result.value = res.toString()
    }
}
