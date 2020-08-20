package com.fphoenixcorneae.demo.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.fphoenixcorneae.dialog.entity.DialogMenuItem
import java.util.*

class TestAdapter(
    private val mContext: Context,
    private val mMenuItems: ArrayList<DialogMenuItem>
) : BaseAdapter() {
    private val mDisplayMetrics: DisplayMetrics
    override fun getCount(): Int {
        return mMenuItems.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val item = mMenuItems[position]
        val ll_item = LinearLayout(mContext)
        ll_item.orientation = LinearLayout.HORIZONTAL
        ll_item.gravity = Gravity.CENTER_VERTICAL
        val iv_item = ImageView(mContext)
        iv_item.setPadding(0, 0, (15 * mDisplayMetrics.density).toInt(), 0)
        ll_item.addView(iv_item)
        val tv_item = TextView(mContext)
        tv_item.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        tv_item.isSingleLine = true
        tv_item.setTextColor(Color.parseColor("#303030"))
        tv_item.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        ll_item.addView(tv_item)
        ll_item.setPadding(
            if (item.mResId == 0) (18 * mDisplayMetrics.density).toInt() else (16 * mDisplayMetrics.density).toInt(),
            (10 * mDisplayMetrics.density).toInt(),
            0,
            (10 * mDisplayMetrics.density).toInt()
        )
        iv_item.setImageResource(item.mResId)
        tv_item.text = item.mOperationName
        iv_item.visibility = if (item.mResId == 0) View.GONE else View.VISIBLE
        return ll_item
    }

    init {
        mDisplayMetrics = DisplayMetrics()
        (mContext as Activity).windowManager.defaultDisplay.getMetrics(mDisplayMetrics)
    }
}