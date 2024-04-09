package com.emanuelgalvao.authserver.users.util

enum class SortDirection {
    ASC,
    DESC
}

fun String.toSortDirection(): SortDirection? {
    return when (this.lowercase()) {
        "asc" -> SortDirection.ASC
        "desc" -> SortDirection.DESC
        else -> null
    }
}