package com.ezetap.assignment

import com.ezetap.network.UIData
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class UIModel : Serializable{
    @SerializedName("logo-url")
    var logo_url: String? = null

    @SerializedName("heading-text")
    var heading_text: String? = null

    var uidata: ArrayList<UIData>? = null
}