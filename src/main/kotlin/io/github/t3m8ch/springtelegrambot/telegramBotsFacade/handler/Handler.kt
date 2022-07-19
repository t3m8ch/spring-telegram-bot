package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.handler

import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.context.Context
import kotlin.reflect.KClass

class Handler(
    val handle: (ctx: Context) -> Unit,
)

class HandlerConfigurationWithoutContextType<out T : Context>(
    configure: HandlerConfigurationWithoutContextType<T>.() -> Unit
) {
    lateinit var filter: (ctx: Context) -> Boolean
        private set

    lateinit var handle: (ctx: Context) -> Unit
        private set

    @Suppress("UNCHECKED_CAST")
    fun filter(filter: (ctx: T) -> Boolean) = apply { this.filter = filter as (Context) -> Boolean }
    @Suppress("UNCHECKED_CAST")
    fun handle(handle: (ctx: T) -> Unit) = apply { this.handle = handle as (Context) -> Unit }

    init {
        configure()
    }
}

abstract class HandlerFactory(configure: HandlerFactory.() -> Unit) {
    lateinit var contextType: KClass<out Context>
        private set

    lateinit var filter: (ctx: Context) -> Boolean
        private set

    lateinit var handle: (ctx: Context) -> Unit
        private set

    init {
        configure()
    }

    fun <T : Context> contextType(
        contextType: KClass<out T>,
        configureGenericTyped: HandlerConfigurationWithoutContextType<T>.() -> Unit
    ) {
        this.contextType = contextType

        val config = HandlerConfigurationWithoutContextType<T> { configureGenericTyped() }
        filter = config.filter
        handle = config.handle
    }

    fun create() = Handler(handle)
}
