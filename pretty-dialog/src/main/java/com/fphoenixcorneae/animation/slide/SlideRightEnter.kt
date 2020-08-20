package com.fphoenixcorneae.animation.slide

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 右边滑入动效
 */
class SlideRightEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "translationX", 300f, 0f),
            ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1f)
        )
    }
}