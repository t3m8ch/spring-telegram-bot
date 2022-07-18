package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.infrastructure

import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.config.TelegramBotConfig
import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.interfaces.Command
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class TelegramDispatcher(private val config: TelegramBotConfig) : TelegramLongPollingBot() {
    private val commands = mutableListOf<Command<*>>()

    override fun getBotToken() = config.botToken
    override fun getBotUsername() = config.botUsername

    override fun onUpdateReceived(update: Update) {
        logger.info("Update has arrived")

        if (update.hasMessage()) {
            logger.info("Update type is Message")

            @Suppress("UNCHECKED_CAST")
            val messageCommand = commands.asSequence()
                .filter { it.objectType == Message::class }
                .firstOrNull { (it as Command<Message>).filter.check(update.message) }

            if (messageCommand == null) {
                logger.info("Update has no command")
                return
            }

            @Suppress("UNCHECKED_CAST")
            (messageCommand as Command<Message>).execute(update.message)

            logger.info("Update executed")
        }
    }

    fun addCommand(command: Command<*>) {
        commands += command
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TelegramDispatcher::class.java)
    }
}
