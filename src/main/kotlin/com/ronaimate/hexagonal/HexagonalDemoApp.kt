package com.ronaimate.hexagonal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class HexagonalDemoApp

fun main(args: Array<String>) {
    runApplication<HexagonalDemoApp>(*args)
}