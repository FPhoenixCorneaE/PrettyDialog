package com.fphoenixcorneae.popup.base

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.fphoenixcorneae.dialog.R
import com.fphoenixcorneae.utils.StatusBarUtils

abstract class BasePopup<T : BasePopup<T>>(context: Context) :
    InternalBasePopup<T>(context) {
    protected var mWrappedView: View
    protected var mLlContent: LinearLayout? = null
    protected var mAlignCenter = false
    abstract fun onCreatePopupView(): View
    override fun onCreateView(): View? {
        val inflate =
            View.inflate(mContext, R.layout.pretty_popup_base, null)
        mLlContent = inflate.findViewById<View>(R.id.ll_content) as LinearLayout
        mLlContent!!.addView(mWrappedView)

        //让mOnCreateView充满父控件,防止ViewHelper.setXY导致点击事件无效
        inflate.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )
        return inflate
    }

    fun offset(xOffset: Float, yOffset: Float): T {
        mXOffset = xOffset
        mYOffset = yOffset
        return this as T
    }

    /** align center of pop and anchorView(弹窗与anchorView中心对齐)  */
    fun alignCenter(alignCenter: Boolean): T {
        mAlignCenter = alignCenter
        return this as T
    }

    override fun anchorView(anchorView: View?): T {
        if (anchorView != null) {
            mAnchorView = anchorView
            val location = IntArray(2)
            mAnchorView!!.getLocationOnScreen(location)
            mX = location[0]
            mY = if (mGravity == Gravity.TOP) {
                location[1] - StatusBarUtils.getHeight(mContext)
            } else {
                (location[1] - StatusBarUtils.getHeight(mContext)
                        + anchorView.height)
            }
        }
        return this as T
    }

    /** At this time, we can get view size in dialog(可以获得对话框内视图大小)  */
    override fun onLayoutObtain() {
        var x = mX
        var y = mY
        if (mGravity == Gravity.TOP) {
            y = mY - mLlContent!!.height
        }
        if (mAlignCenter) {
            x = mX + mAnchorView!!.width / 2 - mLlContent!!.width / 2
        }
        x = getX(x)
        y = getY(y)
        x = getX(x + dp2px(mXOffset))
        y = getY(y + dp2px(mYOffset))
        mLlContent!!.x = x.toFloat()
        mLlContent!!.y = y.toFloat()
    }

    private fun getX(x: Int): Int {
        var x = x
        if (x < 0) {
            x = 0
        }
        if (x + mLlContent!!.width > mDisplayMetrics.widthPixels) {
            x = mDisplayMetrics.widthPixels - mLlContent!!.width
        }
        return x
    }

    private fun getY(y: Int): Int {
        var y = y
        if (y < 0) {
            y = 0
        }
        if (y + mLlContent!!.height > mMaxHeight) {
            y = (mMaxHeight - mLlContent!!.height).toInt()
        }
        return y
    }

    init {
        mWrappedView = onCreatePopupView()
        gravity(Gravity.BOTTOM)
    }
}