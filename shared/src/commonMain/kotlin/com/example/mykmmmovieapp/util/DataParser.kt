package com.example.mykmmmovieapp.util

fun removeTags(title: String): String {
    var new_title = title.replace("<b>", "")
    new_title = new_title.replace("</b>", "")
    new_title = new_title.replace("&amp;", "")
    return new_title
}

fun removeVerticalBarFromText(text: String): String {
    if (text.isEmpty()) return ""
    var newText = text.replace("|", ", ")
    return newText.substring(0, newText.length - 2)
}