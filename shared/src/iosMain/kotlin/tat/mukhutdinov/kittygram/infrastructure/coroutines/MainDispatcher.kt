package tat.mukhutdinov.kittygram.infrastructure.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

// https://github.com/Kotlin/kotlinx.coroutines/issues/462
object MainDispatcher : CoroutineDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) { block.run() }
    }
}