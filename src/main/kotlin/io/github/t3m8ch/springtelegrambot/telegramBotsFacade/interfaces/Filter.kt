package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.interfaces

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject

interface Filter<T : BotApiObject> {
    fun check(obj: T): Boolean
}
