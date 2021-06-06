package com.workindia

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.workindia.models.Item
import com.workindia.models.Response
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val gridIndividualWidth =
            (screenWidth / 3) - (Resources.getSystem().displayMetrics.density * 2) - 50
        adapter = ItemsAdapter(getItems())
        adapter.setGridIndividualWidth(gridIndividualWidth.toInt())
        recyclerView.adapter = adapter
    }

    fun showListView(view: View) {
        adapter.updateManagerType(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun showGridView(view: View) {
        adapter.updateManagerType(false)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    private fun getItems(): List<Item> {
        val jsonString: String?
        try {
            val inputStream = this.assets.open("items.json")

            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            jsonString = String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return arrayListOf()
        }

        val response = Gson().fromJson(jsonString, Response::class.java)
        return response.getData()!!.getItems()!!
    }
}