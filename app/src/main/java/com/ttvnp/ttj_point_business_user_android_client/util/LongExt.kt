package com.ttvnp.ttj_point_business_user_android_client.util

import java.text.NumberFormat

fun Long.formatToKilo(): String {
    val formattedNumber = NumberFormat.getNumberInstance().format(this)
    val kiloNumber = if (formattedNumber.length > 3) {
        formattedNumber.replace(Regex(""",(?=[^,]*$)"""), ".")
    } else {
        val zero = "0".repeat(3 - formattedNumber.length)
        "0.$zero$formattedNumber"
    }
    return "${kiloNumber}K"
}
