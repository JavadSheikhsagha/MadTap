package ir.nikgostarr.madtap.api

import com.google.gson.annotations.SerializedName

data class VersionModel(
    @SerializedName("version")
    val version:String)
