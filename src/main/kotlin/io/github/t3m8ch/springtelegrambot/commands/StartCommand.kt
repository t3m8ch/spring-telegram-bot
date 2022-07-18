package io.github.t3m8ch.springtelegrambot.commands

import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.interfaces.Command
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

@Component
class StartCommand : Command<Message>(Message::class, { it.text == "/start" }) {
    override fun execute(obj: Message) {
        val method = SendMessage.builder().chatId(obj.chatId).text("Start!").build()
        client.execute(method)
    }
}
