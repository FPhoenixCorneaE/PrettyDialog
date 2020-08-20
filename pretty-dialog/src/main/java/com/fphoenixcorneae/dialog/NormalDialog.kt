package com.fphoenixcorneae.dialog

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.fphoenixcorneae.utils.CornerUtils
import com.fphoenixcorneae.dialog.base.BaseAlertDialog

class NormalDialog(context: Context) :
    BaseAlertDialog<NormalDialog>(context) {
    /** title underline  */
    private var mVLineTitle: View? = null

    /** vertical line between btns  */
    private var mVLineVertical: View? = null

    /** vertical line between btns  */
    private var mVLineVertical2: View? = null

    /** horizontal line above btns  */
    private var mVLineHorizontal: View? = null

    /** title underline color(标题下划线颜色)  */
    private var mTitleLineColor = Color.parseColor("#61AEDC")

    /** title underline height(标题下划线高度)  */
    private var mTitleLineHeight = 1f

    /** btn divider line color(对话框之间的分割线颜色(水平+垂直))  */
    private var mDividerColor = Color.parseColor("#DCDCDC")
    private var mStyle = STYLE_ONE
    override fun onCreateView(): View? {
        /** title  */
        mTvTitle.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mLlContainer.addView(mTvTitle)
        /** title underline  */
        mVLineTitle = View(mContext)
        mLlContainer.addView(mVLineTitle)
        /** content  */
        mTvContent.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mLlContainer.addView(mTvContent)
        mVLineHorizontal = View(mContext)
        mVLineHorizontal!!.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            1
        )
        mLlContainer.addView(mVLineHorizontal)
        /** btns  */
        mTvBtnLeft.layoutParams = LinearLayout.LayoutParams(0, dp2px(45f), 1f)
        mLlBtns.addView(mTvBtnLeft)
        mVLineVertical = View(mContext)
        mVLineVertical!!.layoutParams = LinearLayout.LayoutParams(
            1,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        mLlBtns.addView(mVLineVertical)
        mTvBtnMiddle.layoutParams = LinearLayout.LayoutParams(0, dp2px(45f), 1f)
        mLlBtns.addView(mTvBtnMiddle)
        mVLineVertical2 = View(mContext)
        mVLineVertical2!!.layoutParams = LinearLayout.LayoutParams(
            1,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        mLlBtns.addView(mVLineVertical2)
        mTvBtnRight.layoutParams = LinearLayout.LayoutParams(0, dp2px(45f), 1f)
        mLlBtns.addView(mTvBtnRight)
        mLlContainer.addView(mLlBtns)
        return mLlContainer
    }

    override fun setUiBeforeShow() {
        super.setUiBeforeShow()
        /** title  */
        if (mStyle == STYLE_ONE) {
            mTvTitle.minHeight = dp2px(48f)
            mTvTitle.gravity = Gravity.CENTER_VERTICAL
            mTvTitle.setPadding(dp2px(15f), dp2px(5f), dp2px(0f), dp2px(5f))
            mTvTitle.visibility = if (mIsTitleShow) View.VISIBLE else View.GONE
        } else if (mStyle == STYLE_TWO) {
            mTvTitle.gravity = Gravity.CENTER
            mTvTitle.setPadding(dp2px(0f), dp2px(15f), dp2px(0f), dp2px(0f))
        }
        /** title underline  */
        mVLineTitle!!.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp2px(mTitleLineHeight)
        )
        mVLineTitle!!.setBackgroundColor(mTitleLineColor)
        mVLineTitle!!.visibility =
            if (mIsTitleShow && mStyle == STYLE_ONE) View.VISIBLE else View.GONE
        /** content  */
        if (mStyle == STYLE_ONE) {
            mTvContent.setPadding(dp2px(15f), dp2px(10f), dp2px(15f), dp2px(10f))
            mTvContent.minHeight = dp2px(68f)
            mTvContent.gravity = mContentGravity
        } else if (mStyle == STYLE_TWO) {
            mTvContent.setPadding(dp2px(15f), dp2px(7f), dp2px(15f), dp2px(20f))
            mTvContent.minHeight = dp2px(56f)
            mTvContent.gravity = Gravity.CENTER
        }
        /** btns  */
        mVLineHorizontal!!.setBackgroundColor(mDividerColor)
        mVLineVertical!!.setBackgroundColor(mDividerColor)
        mVLineVertical2!!.setBackgroundColor(mDividerColor)
        if (mBtnNum == 1) {
            mTvBtnLeft.visibility = View.GONE
            mTvBtnRight.visibility = View.GONE
            mVLineVertical!!.visibility = View.GONE
            mVLineVertical2!!.visibility = View.GONE
        } else if (mBtnNum == 2) {
            mTvBtnMiddle.visibility = View.GONE
            mVLineVertical!!.visibility = View.GONE
        }
        /**set background color and corner radius  */
        val radius = dp2px(mCornerRadius).toFloat()
        mLlContainer.setBackgroundDrawable(CornerUtils.cornerDrawable(mBgColor, radius))
        mTvBtnLeft.setBackgroundDrawable(
            CornerUtils.btnSelector(
                radius,
                mBgColor,
                mBtnPressColor,
                0
            )
        )
        mTvBtnRight.setBackgroundDrawable(
            CornerUtils.btnSelector(
                radius,
                mBgColor,
                mBtnPressColor,
                1
            )
        )
        mTvBtnMiddle.setBackgroundDrawable(
            CornerUtils.btnSelector(
                if (mBtnNum == 1) radius else 0f,
                mBgColor,
                mBtnPressColor,
                -1
            )
        )
    }
    // --->属性设置
    /** set style(设置style)  */
    fun style(style: Int): NormalDialog {
        mStyle = style
        return this
    }

    /** set title underline color(设置标题下划线颜色)  */
    fun titleLineColor(titleLineColor: Int): NormalDialog {
        mTitleLineColor = titleLineColor
        return this
    }

    /** set title underline height(设置标题下划线高度)  */
    fun titleLineHeight(titleLineHeight_DP: Float): NormalDialog {
        mTitleLineHeight = titleLineHeight_DP
        return this
    }

    /** set divider color between btns(设置btn分割线的颜色)  */
    fun dividerColor(dividerColor: Int): NormalDialog {
        mDividerColor = dividerColor
        return this
    }

    companion object {
        const val STYLE_ONE = 0
        const val STYLE_TWO = 1
    }

    init {
        /** default value */
        mTitleTextColor = Color.parseColor("#61AEDC")
        mTitleTextSize = 22f
        mContentTextColor = Color.parseColor("#383838")
        mContentTextSize = 17f
        mLeftBtnTextColor = Color.parseColor("#8a000000")
        mRightBtnTextColor = Color.parseColor("#8a000000")
        mMiddleBtnTextColor = Color.parseColor("#8a000000")
    }
}