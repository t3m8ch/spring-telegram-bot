package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.interfaces

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import java.io.Serializable

interface TelegramClient {
    fun <T : Serializable> execute(method: BotApiMethod<T>): T
}
