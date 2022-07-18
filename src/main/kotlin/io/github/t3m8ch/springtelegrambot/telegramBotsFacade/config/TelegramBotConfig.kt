package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class TelegramBotConfig(
    @Value("\${telegram.bot-token}") val botToken: String,
    @Value("\${telegram.bot-username}") val botUsername: String,
)
