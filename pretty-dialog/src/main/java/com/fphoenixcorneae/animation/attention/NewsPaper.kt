package com.fphoenixcorneae.animation.attention

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 报纸动效
 */
class NewsPaper : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.5f, 1f),
            ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.5f, 1f),
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f),
            ObjectAnimator.ofFloat(view, "rotation", 1080f, 720f, 360f, 0f)
        )
    }

    init {
        duration = 700
    }
}