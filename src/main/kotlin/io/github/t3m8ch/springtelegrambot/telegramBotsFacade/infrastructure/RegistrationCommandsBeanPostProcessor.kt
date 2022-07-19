package io.github.t3m8ch.springtelegrambot.telegramBotsFacade.infrastructure

import io.github.t3m8ch.springtelegrambot.telegramBotsFacade.handler.HandlerFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component

@Component
class RegistrationCommandsBeanPostProcessor(private val telegramDispatcher: TelegramDispatcher) : BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        if (bean is HandlerFactory) {
            telegramDispatcher.addHandlerFactory(bean)
            logger.info("${bean::class.simpleName} is registered")
        }
        return bean
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TelegramDispatcher::class.java)
    }
}
