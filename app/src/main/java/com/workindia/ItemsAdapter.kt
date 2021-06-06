package com.workindia

import android.graphics.Typeface
import android.text.Spannable
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.RecyclerView
import com.workindia.models.Item
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_grid_item.*
import kotlinx.android.synthetic.main.recycler_linear_item.*
import kotlinx.android.synthetic.main.recycler_linear_item.nameTextView
import kotlinx.android.synthetic.main.recycler_linear_item.priceTextView
import java.util.*

/**
 * Created by sreenu on 5/6/21.
 */
class ItemsAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var gridIndividualWidth: Int = 0
    private var isLinear = true

    override fun getItemViewType(position: Int): Int {
        return if (isLinear) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            ItemsLinearViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_linear_item, parent, false)
            )
        } else {
            ItemsGridViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_grid_item, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemsLinearViewHolder) {
            holder.setUpView(items[position])
        } else {
            (holder as ItemsGridViewHolder).setUpView(items[position], gridIndividualWidth)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateManagerType(isLinear: Boolean) {
        this.isLinear = isLinear
    }

    fun setGridIndividualWidth(gridIndividualWidth: Int) {
        this.gridIndividualWidth = gridIndividualWidth
    }

    class ItemsLinearViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun setUpView(item: Item) {
            nameTextView.text = item.getName()
            val priceText = containerView.context.getString(
                R.string.set_cost, String.format(
                    Locale.getDefault(),
                    "%.1f",
                    item.getPrice()?.split(" ")?.get(1)?.toFloat()
                )
            ).toSpannable()
            priceText.setSpan(
                StyleSpan(Typeface.BOLD),
                5,
                priceText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            priceTextView.text = priceText
            shippingTextView.text = item.getExtra()
        }
    }


    class ItemsGridViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun setUpView(item: Item, gridIndividualWidth: Int) {
            nameTextView.text = item.getName()
            priceTextView.text = String.format(
                Locale.getDefault(),
                "₹%.1f",
                item.getPrice()?.split(" ")?.get(1)?.toFloat()
            )
            val layoutParams = imageView.layoutParams
            layoutParams.width = gridIndividualWidth
            layoutParams.height = gridIndividualWidth
            imageView.layoutParams = layoutParams
        }
    }
}