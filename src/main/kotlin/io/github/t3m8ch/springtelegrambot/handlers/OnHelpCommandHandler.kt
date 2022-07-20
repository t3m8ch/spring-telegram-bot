package io.github.t3m8ch.springtelegrambot.handlers

import io.github.t3m8ch.springtelegrambot.services.TextService
import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.context.impl.MessageContext
import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.handler.Handler
import org.springframework.stereotype.Component

@Component
class OnHelpCommandHandler(private val textService: TextService) : Handler({
    contextType(MessageContext::class) {
        filter { it.message.text == "/help" }
        handle { ctx ->
            ctx.reply(textService.getText("HELP_TEXT")!!)
        }
    }
})
