package com.fphoenixcorneae.demo.extra

import android.content.Context
import android.graphics.Color
import android.view.View
import com.fphoenixcorneae.animation.attention.Swing
import com.fphoenixcorneae.utils.CornerUtils
import com.fphoenixcorneae.dialog.base.BaseDialog
import com.fphoenixcorneae.demo.R
import kotlinx.android.synthetic.main.dialog_custom_base.*

class CustomBaseDialog(context: Context) :
    BaseDialog<CustomBaseDialog>(context) {
    override fun onCreateView(): View {
        widthScale(0.85f)
        showAnim(Swing())

        // dismissAnim(this, new ZoomOutExit());
        val inflate =
            View.inflate(mContext, R.layout.dialog_custom_base, null)
        inflate.setBackgroundDrawable(
            CornerUtils.cornerDrawable(
                Color.parseColor("#ffffff"),
                dp2px(5f).toFloat()
            )
        )
        return inflate
    }

    override fun setUiBeforeShow() {
        mTvCancel!!.setOnClickListener { dismiss() }
        mTvExit!!.setOnClickListener { dismiss() }
    }
}