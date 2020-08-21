package com.fphoenixcorneae.animation.slide

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 顶部滑入动效
 */
class SlideTopEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        val displayMetrics = view.context.resources.displayMetrics
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "translationY", -displayMetrics.heightPixels / 2f, 0f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        )
    }
}