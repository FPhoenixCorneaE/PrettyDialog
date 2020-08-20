package com.fphoenixcorneae.animation.flip

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 顶部翻转进入动效
 */
class FlipTopEnter : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "rotationX", 90f, 0f),
            ObjectAnimator.ofFloat(view, "translationY", -300f, 0f),
            ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1f)
        )
    }
}