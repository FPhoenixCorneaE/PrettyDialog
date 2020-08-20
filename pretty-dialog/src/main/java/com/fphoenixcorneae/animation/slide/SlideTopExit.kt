package com.fphoenixcorneae.animation.slide

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 顶部滑出动效
 */
class SlideTopExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val height = view.measuredHeight.toFloat()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "translationY", 0f, -height),
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        )
    }
}