package com.workindia.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sreenu on 5/6/21.
 */
class Response {
    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("error")
    @Expose
    private var error: Any? = null

    @SerializedName("data")
    @Expose
    private var data: Data? = null

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getError(): Any? {
        return error
    }

    fun setError(error: Any?) {
        this.error = error
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

}