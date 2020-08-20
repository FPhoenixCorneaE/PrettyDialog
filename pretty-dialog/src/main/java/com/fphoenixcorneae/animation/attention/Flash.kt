package com.fphoenixcorneae.animation.attention

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 闪现动效
 */
class Flash : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f, 0f, 1f)
        )
    }

    init {
        duration = 1000
    }
}