package com.fphoenixcorneae.animation.zoom

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 底部放大进入动效
 */
class ZoomInBottomEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val height = view.measuredHeight.toFloat()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1f),
            ObjectAnimator.ofFloat(view, "translationY", height, -60f, 0f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f)
        )
    }

    init {
        duration = 600
    }
}