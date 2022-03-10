package com.ezetap.network

import java.io.Serializable

open class UIData: Serializable {
    var uitype: String? = null
    var value: String? = null
    var key: String? = null
    var hint: String? = null
}