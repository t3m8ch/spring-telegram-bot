package io.github.t3m8ch.springtelegrambot.services.impl

import io.github.t3m8ch.springtelegrambot.services.TextService
import org.springframework.stereotype.Service

@Service
class TextServiceImpl : TextService {
    override fun getText(id: String): String? = when (id) {
        "START_TEXT" -> "Start!"
        "HELP_TEXT" -> "Help!"
        else -> null
    }
}
