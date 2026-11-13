package cn.edu.sicnu.cs.lyx.bookmanager

import java.util.Locale

data class Book(
    val title: String,
    val author: String,
    val publisher: String,
    val price: Double
) {
    val priceText: String
        get() = String.format(Locale.CHINA, "%.2f 元", price)
}
