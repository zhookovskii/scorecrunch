package com.zhukovskii.scorecrunch.util

object HttpCodeTranslator {

    val codeToText: Map<Int, String> = mapOf(
        429 to "Too many requests, chill out",
        500 to "The server is down for whatever reason"
    )
}