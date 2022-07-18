package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.utils

import org.telegram.telegrambots.meta.api.objects.Message

fun Message.getArgs() = text.split("\\s".toRegex()).filterIndexed { idx, _ -> idx > 0 }
fun Message.hasArgs() = getArgs().isNotEmpty()
