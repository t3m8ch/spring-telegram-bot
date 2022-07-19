package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.context.impl

import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.context.Context
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.bots.AbsSender

class MessageContext(val sender: AbsSender, val message: Message) : Context {
    fun answer(text: String): Message {
        val sendMessage = SendMessage.builder().chatId(message.chatId).text(text).build()
        return sender.execute(sendMessage)
    }

    fun reply(text: String): Message {
        val messageId = message.messageId
        val sendMessage = SendMessage.builder().chatId(message.chatId).text(text).replyToMessageId(messageId).build()
        return sender.execute(sendMessage)
    }
}
