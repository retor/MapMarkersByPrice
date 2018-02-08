package pro.retor.mapmarkerstest.data.api.entity

import com.google.gson.annotations.SerializedName

class Marker {

    @SerializedName("lat")
    var lat: Double? = null
    @SerializedName("lon")
    var lon: Double? = null
    @SerializedName("price")
    var price: Long? = null

}
