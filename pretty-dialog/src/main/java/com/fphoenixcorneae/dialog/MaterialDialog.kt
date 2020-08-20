package com.fphoenixcorneae.dialog

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.fphoenixcorneae.utils.CornerUtils
import com.fphoenixcorneae.dialog.base.BaseAlertDialog

/**
 * Dialog like Material Design Dialog
 */
class MaterialDialog(context: Context) :
    BaseAlertDialog<MaterialDialog>(context) {
    override fun onCreateView(): View? {
        /** title  */
        mTvTitle.gravity = Gravity.CENTER_VERTICAL
        mTvTitle.setPadding(dp2px(20f), dp2px(20f), dp2px(20f), dp2px(0f))
        mTvTitle.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mLlContainer.addView(mTvTitle)
        /** content  */
        mTvContent.setPadding(dp2px(20f), dp2px(20f), dp2px(20f), dp2px(20f))
        mTvContent.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mLlContainer.addView(mTvContent)
        /** btns */
        mLlBtns.gravity = Gravity.END
        mLlBtns.addView(mTvBtnLeft)
        mLlBtns.addView(mTvBtnMiddle)
        mLlBtns.addView(mTvBtnRight)
        mTvBtnLeft.setPadding(dp2px(15f), dp2px(8f), dp2px(15f), dp2px(8f))
        mTvBtnRight.setPadding(dp2px(15f), dp2px(8f), dp2px(15f), dp2px(8f))
        mTvBtnMiddle.setPadding(dp2px(15f), dp2px(8f), dp2px(15f), dp2px(8f))
        mLlBtns.setPadding(dp2px(20f), dp2px(0f), dp2px(10f), dp2px(10f))
        mLlContainer.addView(mLlBtns)
        return mLlContainer
    }

    override fun setUiBeforeShow() {
        super.setUiBeforeShow()
        /**set background color and corner radius  */
        val radius = dp2px(mCornerRadius).toFloat()
        mLlContainer.setBackgroundDrawable(CornerUtils.cornerDrawable(mBgColor, radius))
        mTvBtnLeft.setBackgroundDrawable(
            CornerUtils.btnSelector(
                radius,
                mBgColor,
                mBtnPressColor,
                -2
            )
        )
        mTvBtnRight.setBackgroundDrawable(
            CornerUtils.btnSelector(
                radius,
                mBgColor,
                mBtnPressColor,
                -2
            )
        )
        mTvBtnMiddle.setBackgroundDrawable(
            CornerUtils.btnSelector(
                radius,
                mBgColor,
                mBtnPressColor,
                -2
            )
        )
    }

    init {
        /** default value */
        mTitleTextColor = Color.parseColor("#DE000000")
        mTitleTextSize = 22f
        mContentTextColor = Color.parseColor("#8a000000")
        mContentTextSize = 16f
        mLeftBtnTextColor = Color.parseColor("#383838")
        mRightBtnTextColor = Color.parseColor("#468ED0")
        mMiddleBtnTextColor = Color.parseColor("#00796B")
    }
}