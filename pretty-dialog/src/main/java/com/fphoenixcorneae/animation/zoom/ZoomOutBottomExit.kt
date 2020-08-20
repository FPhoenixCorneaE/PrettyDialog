package com.fphoenixcorneae.animation.zoom

import android.animation.ObjectAnimator
import android.view.View
import android.view.View.MeasureSpec
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 缩小底部退出动效
 */
class ZoomOutBottomExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        val h = view.measuredHeight
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 1f, 1f, 0f),
            ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.475f, 0.1f),
            ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.475f, 0.1f),
            ObjectAnimator.ofFloat(view, "translationY", 0f, -60f, h.toFloat())
        )
    }

    init {
        duration = 600
    }
}