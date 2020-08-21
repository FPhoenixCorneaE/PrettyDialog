package com.fphoenixcorneae.dialog.base

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

abstract class BottomBaseDialog<T : BottomBaseDialog<T>> @JvmOverloads constructor(
    context: Context,
    animateView: View? = null
) : BottomTopBaseDialog<T>(context) {
    override val gravity: Int
        get() = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL

    override fun onStart() {
        super.onStart()
        window?.setGravity(gravity)
        mFlTop.setPadding(mLeft, mTop, mRight, mBottom)
    }

    init {
        animatedView(animateView)
        innerShowAnim(
            TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f
            )
        )
        innerDismissAnim(
            TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f
            )
        )
    }
}