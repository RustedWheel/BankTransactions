package com.rustedwheel.android.banktransactions.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Transaction : RealmObject() {

    @PrimaryKey
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