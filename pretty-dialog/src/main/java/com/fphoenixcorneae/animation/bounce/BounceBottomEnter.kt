package com.fphoenixcorneae.animation.bounce

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 底部进入反弹动效
 */
class BounceBottomEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 0f, 0.25f, 0.75f, 1f),
            ObjectAnimator.ofFloat(view, "translationY", 360f, -30f, 10f, 0f)
        )
    }

    init {
        duration = 1000
    }
}