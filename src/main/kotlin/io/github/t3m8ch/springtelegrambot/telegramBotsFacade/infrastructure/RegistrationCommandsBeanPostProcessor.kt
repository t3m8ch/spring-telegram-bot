package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.infrastructure

import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.interfaces.Command
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component

@Component
class RegistrationCommandsBeanPostProcessor(private val telegramDispatcher: TelegramDispatcher) : BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        if (bean is Command<*>) {
            telegramDispatcher.addCommand(bean)
            logger.info("Command '${bean::class.simpleName}' registered")
        }
        return bean
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TelegramDispatcher::class.java)
    }
}
