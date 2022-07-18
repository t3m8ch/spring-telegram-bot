package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.interfaces

import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.impl.LambdaFilter
import org.springframework.beans.factory.annotation.Autowired
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import kotlin.reflect.KClass

abstract class Command<T : BotApiObject>(val objectType: KClass<T>, val filter: Filter<T>) {
    constructor(objectType: KClass<T>, filter: (obj: T) -> Boolean) : this(objectType, LambdaFilter(filter))

    @Autowired
    protected lateinit var client: TelegramClient
        private set

    abstract fun execute(obj: T)
}
