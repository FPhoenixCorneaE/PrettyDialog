package com.fphoenixcorneae.animation.slit

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 狭缝动效
 */
class SlitExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(
                view,
                "rotationY",
                0f,
                45f,
                88f,
                88f,
                90f
            ),
            ObjectAnimator.ofFloat(
                view,
                "alpha",
                1f,
                0.8f,
                0.4f,
                0f
            ),
            ObjectAnimator.ofFloat(
                view,
                "scaleX",
                1f,
                0.9f,
                0.9f,
                0.5f,
                0f
            ),
            ObjectAnimator.ofFloat(
                view,
                "scaleY",
                1f,
                0.9f,
                0.9f,
                0.5f,
                0f
            )
        )
    }

    init {
        duration = 1000
    }
}