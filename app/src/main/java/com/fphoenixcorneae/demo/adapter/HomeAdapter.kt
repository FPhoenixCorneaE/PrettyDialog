package com.fphoenixcorneae.demo.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.fphoenixcorneae.demo.R
import com.fphoenixcorneae.demo.ui.DialogHomeActivity

class HomeAdapter(private val mContext: Context) : BaseExpandableListAdapter() {

    // --->group
    override fun getGroupCount(): Int {
        return DialogHomeActivity.mGroups.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return DialogHomeActivity.mGroups[groupPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.ad_dialog_home, null)
        }
        val tv = convertView?.findViewById<View>(R.id.tv_bubble) as TextView
        tv.text = DialogHomeActivity.mGroups[groupPosition]
        return convertView
    }

    // --->child
    override fun getChildrenCount(groupPosition: Int): Int {
        return DialogHomeActivity.mChilds[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return DialogHomeActivity.mChilds[groupPosition][childPosition]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.ad_dialog_home, null)
        }
        val tv = convertView?.findViewById<View>(R.id.tv_bubble) as? TextView
        val v_line = convertView?.findViewById<View>(R.id.v_line)
        v_line?.visibility = View.INVISIBLE
        tv?.setTextColor(Color.parseColor("#383838"))
        tv?.text = DialogHomeActivity.mChilds[groupPosition][childPosition]
        return convertView!!
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}