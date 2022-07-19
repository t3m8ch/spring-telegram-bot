package io.github.t3m8ch.springtelegrambot.handlers

import io.github.t3m8ch.springtelegrambot.services.TextService
import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.context.impl.MessageContext
import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.handler.HandlerFactory
import org.springframework.stereotype.Component

@Component
class OnStartCommandHandler(private val textService: TextService) : HandlerFactory({
    contextType(MessageContext::class) {
        filter { it.message.text == "/start" }
        handle { ctx ->
            ctx.answer(textService.getText("START_TEXT")!!)
        }
    }
})
