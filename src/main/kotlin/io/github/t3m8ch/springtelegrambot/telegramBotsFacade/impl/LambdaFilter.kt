package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.impl

import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.interfaces.Filter
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject

class LambdaFilter<T : BotApiObject>(private val filter: (obj: T) -> Boolean) : Filter<T> {
    override fun check(obj: T) = filter(obj)
}
