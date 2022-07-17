package io.github.t3m8ch.springtelegrambot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringTelegramBotApplication

fun main(args: Array<String>) {
    runApplication<SpringTelegramBotApplication>(*args)
}
