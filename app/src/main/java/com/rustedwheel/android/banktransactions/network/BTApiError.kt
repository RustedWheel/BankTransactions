package com.rustedwheel.android.banktransactions.network

import okhttp3.Response
import java.lang.RuntimeException

class BTApiError(val response: Response) : RuntimeException() {
    val code = response.code
    override val message = response.message
}