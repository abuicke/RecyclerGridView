package ie.moses.recyclergridview.util

fun Number.isNaturalNumber() = (toDouble() - toInt()) == 0.0 && toInt() >= 0