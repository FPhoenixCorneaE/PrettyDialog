package com.fphoenixcorneae.animation.zoom

import android.animation.ObjectAnimator
import android.view.View
import android.view.View.MeasureSpec
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 右边放大进入动效
 */
class ZoomInRightEnter : BaseAnimatorSet() {
    override fun setAnimation(view: View) {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        val width = view.measuredWidth.toFloat()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1f),
            ObjectAnimator.ofFloat(view, "translationX", width, -48f, 0f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f)
        )
    }

    init {
        duration = 750
    }
}