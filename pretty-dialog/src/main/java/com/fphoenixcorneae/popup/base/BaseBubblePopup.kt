package com.fphoenixcorneae.popup.base

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.fphoenixcorneae.animation.bounce.BounceLeftEnter
import com.fphoenixcorneae.animation.fade.FadeExit
import com.fphoenixcorneae.dialog.R
import com.fphoenixcorneae.utils.CornerUtils
import com.fphoenixcorneae.utils.StatusBarUtils
import com.fphoenixcorneae.widget.TriangleView

/**
 * Use dialog to realize bubble style popup(利用Dialog实现泡泡样式的弹窗)
 * thanks https://github.com/michaelye/EasyDialog
 */
abstract class BaseBubblePopup<T : BaseBubblePopup<T>> :
    InternalBasePopup<T> {
    protected var mWrappedView: View?
    protected var mLlContent: LinearLayout? = null
    protected var mTriangleView: TriangleView? = null
    protected var mLayoutParams: RelativeLayout.LayoutParams? = null
    protected var mBubbleColor = 0
    protected var mCornerRadius = 0
    protected var mMarginLeft = 0
    protected var mMarginRight = 0
    protected var triangleWidth = 0
    protected var triangleHeight = 0
    private var mTriangleLayoutParams: RelativeLayout.LayoutParams? = null

    constructor(context: Context) : super(context) {
        mWrappedView = onCreateBubbleView()
        init()
    }

    constructor(
        context: Context,
        wrappedView: View?
    ) : super(context) {
        mWrappedView = wrappedView
        init()
    }

    private fun init() {
        showAnim(BounceLeftEnter())
        dismissAnim(FadeExit())
        dimEnabled(false)
        bubbleColor(Color.parseColor("#BB000000"))
        cornerRadius(5f)
        margin(8f, 8f)
        gravity(Gravity.TOP)
        triangleWidth(24f)
        triangleHeight(12f)
    }

    abstract fun onCreateBubbleView(): View?
    override fun onCreateView(): View? {
        val inflate =
            View.inflate(mContext, R.layout.pretty_popup_bubble, null)
        mLlContent = inflate.findViewById<View>(R.id.ll_content) as LinearLayout
        mTriangleView = inflate.findViewById<View>(R.id.triangle_view) as TriangleView
        mLlContent!!.addView(mWrappedView)
        mLayoutParams = mLlContent!!.layoutParams as RelativeLayout.LayoutParams
        mTriangleLayoutParams = mTriangleView!!.layoutParams as RelativeLayout.LayoutParams
        //让mOnCreateView充满父控件,防止ViewHelper.setXY导致点击事件无效
        inflate.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )
        return inflate
    }

    override fun setUiBeforeShow() {
        mLlContent!!.setBackgroundDrawable(
            CornerUtils.cornerDrawable(mBubbleColor, mCornerRadius.toFloat())
        )
        mLayoutParams!!.setMargins(mMarginLeft, 0, mMarginRight, 0)
        mLlContent!!.layoutParams = mLayoutParams
        mTriangleView!!.color=mBubbleColor
        mTriangleView!!.gravity=if (mGravity == Gravity.TOP) Gravity.BOTTOM else Gravity.TOP
        mTriangleLayoutParams!!.width = triangleWidth
        mTriangleLayoutParams!!.height = triangleHeight
        mTriangleView!!.layoutParams = mTriangleLayoutParams
    }

    override fun onLayoutObtain() {
        mTriangleView!!.x = mX - mTriangleView!!.width / 2.toFloat()
        if (mGravity == Gravity.TOP) {
            val y = mY - mTriangleView!!.height
            mTriangleView!!.y = y.toFloat()
            mLlContent!!.y = y - mLlContent!!.height.toFloat()
        } else {
            mTriangleView!!.y = mY.toFloat()
            mLlContent!!.y = mY + mTriangleView!!.height.toFloat()
        }
        /**
         * mX--->三角形中心距离屏幕左边距离
         * mDisplayMetrics.widthPixels - mX--->三角形中心距离屏幕右边距离
         */
        val availableLeft = mX - mLayoutParams!!.leftMargin //左侧最大可用距离
        val availableRight =
            mDisplayMetrics.widthPixels - mX - mLayoutParams!!.rightMargin //右侧最大可用距离
        var x = 0
        val contentWidth = mLlContent!!.width
        x = if (contentWidth / 2 <= availableLeft && contentWidth / 2 <= availableRight) {
            mX - contentWidth / 2
        } else {
            if (availableLeft <= availableRight) {
                //三角形在屏幕中心的左边
                mLayoutParams!!.leftMargin
            } else {
                //三角形在屏幕中心的右边
                mDisplayMetrics.widthPixels - (contentWidth + mLayoutParams!!.rightMargin)
            }
        }
        mLlContent!!.x = x.toFloat()
    }

    override fun anchorView(anchorView: View?): T {
        if (anchorView != null) {
            mAnchorView = anchorView
            val location = IntArray(2)
            mAnchorView!!.getLocationOnScreen(location)
            mX = location[0] + anchorView.width / 2
            mY = if (mGravity == Gravity.TOP) {
                (location[1] - StatusBarUtils.getHeight(mContext)
                        - dp2px(1f))
            } else {
                location[1] - StatusBarUtils.getHeight(mContext) + anchorView.height + dp2px(1f)
            }
        }
        return this as T
    }

    fun bubbleColor(bubbleColor: Int): T {
        mBubbleColor = bubbleColor
        return this as T
    }

    fun cornerRadius(cornerRadius: Float): T {
        mCornerRadius = dp2px(cornerRadius)
        return this as T
    }

    fun margin(marginLeft: Float, marginRight: Float): T {
        mMarginLeft = dp2px(marginLeft)
        mMarginRight = dp2px(marginRight)
        return this as T
    }

    fun triangleWidth(width: Float): T {
        triangleWidth = dp2px(width)
        return this as T
    }

    fun triangleHeight(height: Float): T {
        triangleHeight = dp2px(height)
        return this as T
    }
}