package com.kucingselfie.jetpacksubmission.util

import com.kucingselfie.jetpacksubmission.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {
    companion object {
        private val instant = Executor { it.run() }
    }
}