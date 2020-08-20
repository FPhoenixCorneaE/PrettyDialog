package com.fphoenixcorneae.popup.base

import android.content.Context
import android.view.Gravity
import android.view.View
import com.fphoenixcorneae.utils.StatusBarUtils
import com.fphoenixcorneae.dialog.base.BaseDialog

/** Base class to help create PopupWindow Style Dialog(实现PopupWindow风格对话框基类)  */
abstract class InternalBasePopup<T : InternalBasePopup<T>>(context: Context) :
    BaseDialog<T>(context) {
    protected var mAnchorView: View? = null
    protected var mX = 0
    protected var mY = 0

    /** BubblePopup位于给定位置上方(Gravity.Top)或者下方(Gravity.Bottom)  */
    protected var mGravity = 0
    protected var mXOffset = 0f
    protected var mYOffset = 0f
    protected var isLayoutObtain = false
    override fun onViewCreated(inflate: View?) {
        mLlControlHeight.clipChildren = false
        inflate?.viewTreeObserver?.addOnGlobalLayoutListener {
            isLayoutObtain = true
            onLayoutObtain()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mOnCreateView?.isClickable = false
        if (isLayoutObtain) {
            onLayoutObtain()
        }
    }

    /** At this time, we can get view size in dialog(可以获得对话框内视图大小)  */
    abstract fun onLayoutObtain()
    abstract fun anchorView(anchorView: View?): T

    /** Gravity.Top or Gravity.Bottom of given positon  */
    fun gravity(gravity: Int): T {
        require(!(gravity != Gravity.TOP && gravity != Gravity.BOTTOM)) { "Gravity must be either Gravity.TOP or Gravity.BOTTOM" }
        mGravity = gravity
        anchorView(mAnchorView)
        return this as T
    }

    fun location(x: Int, y: Int): T {
        mX = x
        mY = y - StatusBarUtils.getHeight(mContext)
        return this as T
    }

    init {
        heightScale(1f)
        dimEnabled(false)
    }
}