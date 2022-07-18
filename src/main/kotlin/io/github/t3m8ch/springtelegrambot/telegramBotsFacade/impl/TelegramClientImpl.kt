package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.impl

import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.infrastructure.TelegramDispatcher
import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.interfaces.TelegramClient
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import java.io.Serializable

@Component
class TelegramClientImpl(private val dispatcher: TelegramDispatcher) : TelegramClient {
    override fun <T : Serializable> execute(method: BotApiMethod<T>): T = dispatcher.execute(method)
}
