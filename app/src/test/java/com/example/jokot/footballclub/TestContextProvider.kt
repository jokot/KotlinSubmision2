package com.example.jokot.footballclub

import com.example.jokot.footballclub.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext


class TestContextProvider : CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined

}