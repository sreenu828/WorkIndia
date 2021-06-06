package com.workindia.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




/**
 * Created by sreenu on 5/6/21.
 */
class Item {
    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("price")
    @Expose
    private var price: String? = null

    @SerializedName("extra")
    @Expose
    private var extra: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String?) {
        this.price = price
    }

    fun getExtra(): String? {
        return extra
    }

    fun setExtra(extra: String?) {
        this.extra = extra
    }
}