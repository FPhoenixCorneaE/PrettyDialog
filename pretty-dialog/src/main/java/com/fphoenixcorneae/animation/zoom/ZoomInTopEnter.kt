package com.fphoenixcorneae.animation.zoom

import android.animation.ObjectAnimator
import android.view.View
import android.view.View.MeasureSpec
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 顶部放大进入动效
 */
class ZoomInTopEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        val height = view.measuredHeight.toFloat()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f),
            ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1f),
            ObjectAnimator.ofFloat(view, "translationY", -height, 60f, 0f)
        )
    }

    init {
        duration = 600
    }
}