package com.example678.stopwatch.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example678.stopwatch.utils.StopwatchState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StopwatchViewModel : ViewModel() {

    private val _time = MutableStateFlow(0L)
    val time: StateFlow<Long> = _time

    private val _state = MutableStateFlow<StopwatchState>(StopwatchState.Stopped)
    val state: StateFlow<StopwatchState> = _state

    private var startTime = 0L
    private var elapsedTime = 0L

    fun start() {
        if (_state.value != StopwatchState.Running) {
            _state.value = StopwatchState.Running
            startTime = System.currentTimeMillis() - elapsedTime
            Log.d("ShowSystemTime", " Realtime : ${System.currentTimeMillis()}")

            viewModelScope.launch {
                while (_state.value == StopwatchState.Running) {
                    elapsedTime = System.currentTimeMillis() - startTime

                    _time.value = elapsedTime
                    delay(10)

                    Log.d("ShowSystemTime22", " Realtime : ${System.currentTimeMillis()}")
                }
            }
        }
    }

    fun pause() {
        if (_state.value == StopwatchState.Running) {
            _state.value = StopwatchState.Paused
        }
    }

    fun reset() {
        _state.value = StopwatchState.Stopped
        elapsedTime = 0L
        _time.value = 0L
    }
}
