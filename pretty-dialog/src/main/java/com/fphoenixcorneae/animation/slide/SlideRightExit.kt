package com.fphoenixcorneae.animation.slide

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 右边滑出动效
 */
class SlideRightExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        val displayMetrics = view.context.resources.displayMetrics
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "translationX", 0f, displayMetrics.widthPixels / 2f),
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        )
    }
}