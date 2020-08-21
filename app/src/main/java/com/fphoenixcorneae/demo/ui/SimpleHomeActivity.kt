package com.fphoenixcorneae.demo.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.fphoenixcorneae.demo.ui.DialogHomeActivity

class SimpleHomeActivity : AppCompatActivity() {
    private val mContext: Context = this
    private val mItems = arrayOf("Dialog", "Popup")
    private val mClazzs = arrayOf<Class<*>>(
        DialogHomeActivity::class.java, PopupHomeActivity::class.java
    )
    private var mDisplayMetrics: DisplayMetrics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDisplayMetrics = resources.displayMetrics
        val lv = ListView(mContext)
        lv.cacheColorHint = Color.TRANSPARENT
        lv.setBackgroundColor(Color.WHITE)
        lv.setFadingEdgeLength(0)
        lv.adapter = SimpleHomeAdapter()
        lv.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val intent = Intent(mContext, mClazzs[position])
            startActivity(intent)
        }
        val layoutParams = FrameLayout.LayoutParams(-1, -2)
        layoutParams.gravity = Gravity.CENTER
        lv.layoutParams = layoutParams
        setContentView(lv)
        window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    }

    internal inner class SimpleHomeAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return mItems.size
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
            val padding = (mDisplayMetrics!!.density * 20).toInt()
            val tv = TextView(mContext)
            tv.text = mItems[position]
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            tv.setTextColor(Color.parseColor("#468ED0"))
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(padding, padding, padding, padding)
            val lp = AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT,
                AbsListView.LayoutParams.WRAP_CONTENT
            )
            tv.layoutParams = lp
            return tv
        }
    }
}