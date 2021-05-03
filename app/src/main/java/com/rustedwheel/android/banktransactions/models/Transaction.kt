package com.rustedwheel.android.banktransactions.models

import java.util.*

class Transaction {

    var id: Int = 0; private set

    var transactionDate: Date = Date(); private set

    var summaryDescription: String = ""; private set

    var debitAmount: Double = 0.0; private set

    var creditAmount: Double = 0.0; private set

}