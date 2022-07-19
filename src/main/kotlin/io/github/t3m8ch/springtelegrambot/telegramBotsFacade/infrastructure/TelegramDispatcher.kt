package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.infrastructure

import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.config.TelegramBotConfig
import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.context.Context
import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.context.impl.MessageContext
import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.handler.HandlerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import kotlin.reflect.KClass

@Component
class TelegramDispatcher(private val config: TelegramBotConfig) : TelegramLongPollingBot() {
    private val handlerFactories: MutableList<HandlerFactory> = mutableListOf()
    fun addHandlerFactory(factory: HandlerFactory) = run { handlerFactories += factory }

    override fun getBotToken() = config.botToken
    override fun getBotUsername() = config.botUsername

    override fun onUpdateReceived(update: Update) {
        val updateType = getUpdateType(update) ?: return
        val contextType = getContextType(updateType) ?: return

        val specificTypeHandlersFactory = handlerFactories.asSequence().filter { it.contextType == contextType }
        val context = createContext(contextType, update) ?: return
        val filteredHandlers = specificTypeHandlersFactory.firstOrNull { it.filter(context) } ?: return

        filteredHandlers.create().handle(context)
    }

    private fun getUpdateType(update: Update): KClass<out BotApiObject>? = when {
        update.hasMessage() -> Message::class
        else -> null
    }

    private fun getContextType(updateType: KClass<out BotApiObject>): KClass<out Context>? = when (updateType) {
        Message::class -> MessageContext::class
        else -> null
    }

    private fun createContext(contextType: KClass<out Context>, update: Update): Context? = when (contextType) {
        MessageContext::class -> MessageContext(this, update.message)
        else -> null
    }
}
