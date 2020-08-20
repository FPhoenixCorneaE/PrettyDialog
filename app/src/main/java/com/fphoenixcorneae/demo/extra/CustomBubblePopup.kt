package com.fphoenixcorneae.demo.extra

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.fphoenixcorneae.popup.base.BaseBubblePopup
import com.fphoenixcorneae.demo.R
import com.fphoenixcorneae.demo.utils.T

class CustomBubblePopup(context: Context) :
    BaseBubblePopup<CustomBubblePopup>(context) {
    private var mIvBubble: ImageView? = null
    private var mTvBubble: TextView? = null
    override fun onCreateBubbleView(): View {
        val inflate =
            View.inflate(mContext, R.layout.popup_bubble_image, null)
        mTvBubble = inflate.findViewById<View>(R.id.tv_bubble) as TextView
        mIvBubble =
            inflate.findViewById<View>(R.id.iv_bubble) as ImageView
        return inflate
    }

    override fun setUiBeforeShow() {
        super.setUiBeforeShow()
        mTvBubble!!.setOnClickListener { T.showShort(mContext, "mTvBubble--->") }
        mIvBubble!!.setOnClickListener { T.showShort(mContext, "mIvBubble--->") }
    }
}