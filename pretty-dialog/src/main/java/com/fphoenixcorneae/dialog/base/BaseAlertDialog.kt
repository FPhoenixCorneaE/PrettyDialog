package com.fphoenixcorneae.dialog.base

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

abstract class BaseAlertDialog<T : BaseAlertDialog<T>>(context: Context) :
    BaseDialog<T>(context) {
    /** container  */
    protected var mLlContainer: LinearLayout
    //title
    /** title  */
    protected var mTvTitle: TextView

    /** title content(标题)  */
    protected var mTitle: String? = null

    /** title textColor(标题颜色)  */
    protected var mTitleTextColor = 0

    /** title textSize(标题字体大小,单位sp)  */
    protected var mTitleTextSize = 0f

    /** enable title show(是否显示标题)  */
    protected var mIsTitleShow = true
    //content
    /** content  */
    protected var mTvContent: TextView

    /** content text  */
    protected var mContent: String? = null

    /** show gravity of content(正文内容显示位置)  */
    protected var mContentGravity = Gravity.CENTER_VERTICAL

    /** content textColor(正文字体颜色)  */
    protected var mContentTextColor = 0

    /** content textSize(正文字体大小)  */
    protected var mContentTextSize = 0f
    //btns
    /** num of btns, [1,3]  */
    protected var mBtnNum = 2

    /** btn container  */
    protected var mLlBtns: LinearLayout

    /** btns  */
    protected var mTvBtnLeft: TextView
    protected var mTvBtnRight: TextView
    protected var mTvBtnMiddle: TextView

    /** btn text(按钮内容)  */
    protected var mBtnLeftText = "取消"
    protected var mBtnRightText = "确定"
    protected var mBtnMiddleText = "继续"

    /** btn textColor(按钮字体颜色)  */
    protected var mLeftBtnTextColor = 0
    protected var mRightBtnTextColor = 0
    protected var mMiddleBtnTextColor = 0

    /** btn textSize(按钮字体大小)  */
    protected var mLeftBtnTextSize = 15f
    protected var mRightBtnTextSize = 15f
    protected var mMiddleBtnTextSize = 15f

    /** btn press color(按钮点击颜色)  */
    protected var mBtnPressColor =
        Color.parseColor("#E3E3E3") // #85D3EF,#ffcccccc,#E3E3E3

    /** left btn click listener(左按钮点击监听)  */
    protected var mOnBtnLeftClickListener: ((dialog: T) -> Unit)? = null

    /** right btn click listener(右按钮点击监听)  */
    protected var mOnBtnRightClickListener: ((dialog: T) -> Unit)? = null

    /** middle btn click listener(中间按钮点击监听)  */
    protected var mOnBtnMiddleClickListener: ((dialog: T) -> Unit)? = null

    /** corner radius,dp(圆角程度,单位dp)  */
    protected var mCornerRadius = 3f

    /** background color(背景颜色)  */
    protected var mBgColor = Color.parseColor("#ffffff")
    override fun setUiBeforeShow() {
        /** title  */
        mTvTitle.visibility = if (mIsTitleShow) View.VISIBLE else View.GONE
        mTvTitle.text = if (TextUtils.isEmpty(mTitle)) "温馨提示" else mTitle
        mTvTitle.setTextColor(mTitleTextColor)
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleTextSize)
        /** content  */
        mTvContent.gravity = mContentGravity
        mTvContent.text = mContent
        mTvContent.setTextColor(mContentTextColor)
        mTvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, mContentTextSize)
        mTvContent.setLineSpacing(0f, 1.3f)
        /**btns */
        mTvBtnLeft.text = mBtnLeftText
        mTvBtnRight.text = mBtnRightText
        mTvBtnMiddle.text = mBtnMiddleText
        mTvBtnLeft.setTextColor(mLeftBtnTextColor)
        mTvBtnRight.setTextColor(mRightBtnTextColor)
        mTvBtnMiddle.setTextColor(mMiddleBtnTextColor)
        mTvBtnLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, mLeftBtnTextSize)
        mTvBtnRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, mRightBtnTextSize)
        mTvBtnMiddle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mMiddleBtnTextSize)
        if (mBtnNum == 1) {
            mTvBtnLeft.visibility = View.GONE
            mTvBtnRight.visibility = View.GONE
        } else if (mBtnNum == 2) {
            mTvBtnMiddle.visibility = View.GONE
        }
        mTvBtnLeft.setOnClickListener {
            if (mOnBtnLeftClickListener != null) {
                mOnBtnLeftClickListener!!.invoke(this as T)
            } else {
                dismiss()
            }
        }
        mTvBtnRight.setOnClickListener {
            if (mOnBtnRightClickListener != null) {
                mOnBtnRightClickListener!!.invoke(this as T)
            } else {
                dismiss()
            }
        }
        mTvBtnMiddle.setOnClickListener {
            if (mOnBtnMiddleClickListener != null) {
                mOnBtnMiddleClickListener!!.invoke(this as T)
            } else {
                dismiss()
            }
        }
    }

    /** set title text(设置标题内容) @return MaterialDialog  */
    fun title(title: String?): T {
        mTitle = title
        return this as T
    }

    /** set title textColor(设置标题字体颜色)  */
    fun titleTextColor(titleTextColor: Int): T {
        mTitleTextColor = titleTextColor
        return this as T
    }

    /** set title textSize(设置标题字体大小)  */
    fun titleTextSize(titleTextSize_SP: Float): T {
        mTitleTextSize = titleTextSize_SP
        return this as T
    }

    /** enable title show(设置标题是否显示)  */
    fun isTitleShow(isTitleShow: Boolean): T {
        mIsTitleShow = isTitleShow
        return this as T
    }

    /** set content text(设置正文内容)  */
    fun content(content: String?): T {
        mContent = content
        return this as T
    }

    /** set content gravity(设置正文内容,显示位置)  */
    fun contentGravity(contentGravity: Int): T {
        mContentGravity = contentGravity
        return this as T
    }

    /** set content textColor(设置正文字体颜色)  */
    fun contentTextColor(contentTextColor: Int): T {
        mContentTextColor = contentTextColor
        return this as T
    }

    /** set content textSize(设置正文字体大小,单位sp)  */
    fun contentTextSize(contentTextSize_SP: Float): T {
        mContentTextSize = contentTextSize_SP
        return this as T
    }

    /**
     * set btn text(设置按钮文字内容)
     * btnTexts size 1, middle
     * btnTexts size 2, left right
     * btnTexts size 3, left right middle
     */
    fun btnNum(btnNum: Int): T {
        check(!(btnNum < 1 || btnNum > 3)) { "btnNum is [1,3]!" }
        mBtnNum = btnNum
        return this as T
    }

    /**
     * set btn text(设置按钮文字内容)
     * btnTexts size 1, middle
     * btnTexts size 2, left right
     * btnTexts size 3, left right middle
     */
    fun btnText(vararg btnTexts: String): T {
        check(!(btnTexts.isEmpty() || btnTexts.size > 3)) { " range of param btnTexts length is [1,3]!" }
        when (btnTexts.size) {
            1 -> {
                mBtnMiddleText = btnTexts[0]
            }
            2 -> {
                mBtnLeftText = btnTexts[0]
                mBtnRightText = btnTexts[1]
            }
            3 -> {
                mBtnLeftText = btnTexts[0]
                mBtnRightText = btnTexts[1]
                mBtnMiddleText = btnTexts[2]
            }
        }
        return this as T
    }

    /**
     * set btn textColor(设置按钮字体颜色)
     * btnTextColors size 1, middle
     * btnTextColors size 2, left right
     * btnTextColors size 3, left right middle
     */
    fun btnTextColor(vararg btnTextColors: Int): T {
        check(!(btnTextColors.isEmpty() || btnTextColors.size > 3)) { " range of param textColors length is [1,3]!" }
        when (btnTextColors.size) {
            1 -> {
                mMiddleBtnTextColor = btnTextColors[0]
            }
            2 -> {
                mLeftBtnTextColor = btnTextColors[0]
                mRightBtnTextColor = btnTextColors[1]
            }
            3 -> {
                mLeftBtnTextColor = btnTextColors[0]
                mRightBtnTextColor = btnTextColors[1]
                mMiddleBtnTextColor = btnTextColors[2]
            }
        }
        return this as T
    }

    /**
     * set btn textSize(设置字体大小,单位sp)
     * btnTextSizes size 1, middle
     * btnTextSizes size 2, left right
     * btnTextSizes size 3, left right middle
     */
    fun btnTextSize(vararg btnTextSizes: Float): T {
        check(!(btnTextSizes.isEmpty() || btnTextSizes.size > 3)) { " range of param btnTextSizes length is [1,3]!" }
        when (btnTextSizes.size) {
            1 -> {
                mMiddleBtnTextSize = btnTextSizes[0]
            }
            2 -> {
                mLeftBtnTextSize = btnTextSizes[0]
                mRightBtnTextSize = btnTextSizes[1]
            }
            3 -> {
                mLeftBtnTextSize = btnTextSizes[0]
                mRightBtnTextSize = btnTextSizes[1]
                mMiddleBtnTextSize = btnTextSizes[2]
            }
        }
        return this as T
    }

    /** set btn press color(设置按钮点击颜色)  */
    fun btnPressColor(btnPressColor: Int): T {
        mBtnPressColor = btnPressColor
        return this as T
    }

    /** set corner radius (设置圆角程度)  */
    fun cornerRadius(cornerRadius_DP: Float): T {
        mCornerRadius = cornerRadius_DP
        return this as T
    }

    /** set background color(设置背景色)  */
    fun bgColor(bgColor: Int): T {
        mBgColor = bgColor
        return this as T
    }

    /**
     * set btn click listener(设置按钮监听事件)
     * onBtnClickListeners size 1, middle
     * onBtnClickListeners size 2, left right
     * onBtnClickListeners size 3, left right middle
     */
    fun setOnBtnClickListeners(vararg onBtnClickListeners: ((dialog: T) -> Unit)?): T {
        check(!(onBtnClickListeners.isEmpty() || onBtnClickListeners.size > 3)) { " range of param onBtnClickListeners length is [1,3]!" }
        when (onBtnClickListeners.size) {
            1 -> {
                mOnBtnMiddleClickListener = onBtnClickListeners[0]
            }
            2 -> {
                mOnBtnLeftClickListener = onBtnClickListeners[0]
                mOnBtnRightClickListener = onBtnClickListeners[1]
            }
            3 -> {
                mOnBtnLeftClickListener = onBtnClickListeners[0]
                mOnBtnRightClickListener = onBtnClickListeners[1]
                mOnBtnMiddleClickListener = onBtnClickListeners[2]
            }
        }
        return this as T
    }

    /**
     * method execute order:
     * show:constructor---show---onCreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     */
    init {
        widthScale(0.88f)
        mLlContainer = LinearLayout(context)
        mLlContainer.orientation = LinearLayout.VERTICAL
        /** title  */
        mTvTitle = TextView(context)
        /** content  */
        mTvContent = TextView(context)
        /**btns */
        mLlBtns = LinearLayout(context)
        mLlBtns.orientation = LinearLayout.HORIZONTAL
        mTvBtnLeft = TextView(context)
        mTvBtnLeft.gravity = Gravity.CENTER
        mTvBtnMiddle = TextView(context)
        mTvBtnMiddle.gravity = Gravity.CENTER
        mTvBtnRight = TextView(context)
        mTvBtnRight.gravity = Gravity.CENTER
    }
}