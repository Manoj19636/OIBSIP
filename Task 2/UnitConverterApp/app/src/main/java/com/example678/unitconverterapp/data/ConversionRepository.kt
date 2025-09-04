package com.example678.unitconverterapp.data



class ConversionRepository {
    fun convert(value: Double, fromUnit: String, toUnit: String): Double {
        return when (fromUnit to toUnit) {
            "cm" to "m" -> value / 100
            "m" to "cm" -> value * 100
            "g" to "kg" -> value / 1000
            "kg" to "g" -> value * 1000
            else -> value // same unit
        }
    }
}
