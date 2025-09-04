package com.example678.stopwatch.utils

sealed class StopwatchState {
    object Running : StopwatchState()
    object Paused : StopwatchState()
    object Stopped : StopwatchState()
}
