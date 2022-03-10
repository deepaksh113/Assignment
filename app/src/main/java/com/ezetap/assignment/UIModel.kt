package com.ezetap.network

import java.io.Serializable

open class UIModel : Serializable{
    var logo_url: String? = null
    var heading_text: String? = null
    var uidata: ArrayList<UIData>? = null
}