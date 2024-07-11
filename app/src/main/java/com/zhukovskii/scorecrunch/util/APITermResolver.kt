package com.zhukovskii.scorecrunch.util

import java.util.Locale

object APITermResolver {
    private val resolver: Map<String, String> = mapOf(
        "REGULAR" to "Full time",
        "QUARTER_FINALS" to "Quarter-finals",
        "SEMI_FINALS" to "Semi-finals"
    )

    private fun format(s: String): String {
        return s.replace("_", " ")
            .lowercase()
            .replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            }
    }

    fun resolve(s: String): String {
        return if (s.isEmpty()) "" else resolver[s] ?: format(s)
    }
}