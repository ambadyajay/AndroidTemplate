package com.example.androidprojecttemplate.core.networking.response

import com.google.gson.annotations.SerializedName

data class AndroidTemplateResponse<T> (
    @SerializedName("Message")
    val message: String?, // Request successful.
    @SerializedName("StatusCode")
    val statusCode: Int?, // 200
    @SerializedName("Version")
    val version: String?, // 3.0.0.0
    @SerializedName("Result")
    val result: T?, // result

    @SerializedName("HasNext")
    val hasNext: Boolean?,
    @SerializedName("HasPrevious")
    val hasPrevious: Boolean?
)
