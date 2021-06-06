package com.workindia.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




/**
 * Created by sreenu on 5/6/21.
 */
class Data {
    @SerializedName("items")
    @Expose
    private var items: List<Item>? = null

    fun getItems(): List<Item>? {
        return items
    }

    fun setItems(items: List<Item>?) {
        this.items = items
    }
}