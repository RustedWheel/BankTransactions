package com.rustedwheel.android.banktransactions.utils

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

fun Date.formatDate(): String = dateFormat.format(this)

fun Double.formatDouble(n: Int, commaSeparateThousands: Boolean = false): String =
    DecimalFormat("${if(commaSeparateThousands) "#,#" else "" }#0.${"0".repeat(n)}").format(this)