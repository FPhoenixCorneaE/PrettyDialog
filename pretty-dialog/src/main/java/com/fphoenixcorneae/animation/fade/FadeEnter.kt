package com.fphoenixcorneae.animation.fade

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 淡入动效
 */
class FadeEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        )
    }

    init {
        duration = 1000
    }
}