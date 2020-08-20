package com.fphoenixcorneae.animation.slide

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 底部滑入动效
 */
class SlideBottomEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "translationY", 300f, 0f),
            ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1f)
        )
    }
}