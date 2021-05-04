package com.rustedwheel.android.banktransactions.models

import com.google.gson.annotations.SerializedName
import java.util.*

class Transaction {

    @SerializedName("id")
    var id: Int = 0; private set

    @SerializedName("transactionDate")
    var transactionDate: Date = Date(); private set

    @SerializedName("summary")
    var summaryDescription: String = ""; private set

    @SerializedName("debit")
    var debitAmount: Double = 0.0; private set

    @SerializedName("credit")
    var creditAmount: Double = 0.0; private set

    val isCredit: Boolean
        get() = creditAmount != 0.0
}