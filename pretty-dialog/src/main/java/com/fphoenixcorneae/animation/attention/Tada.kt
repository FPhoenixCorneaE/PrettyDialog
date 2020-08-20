package com.fphoenixcorneae.animation.attention

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 缩放摇摆动效
 */
class Tada : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(
                view,
                "scaleX",
                1f,
                0.9f,
                0.9f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1f
            ),
            ObjectAnimator.ofFloat(
                view,
                "scaleY",
                1f,
                0.9f,
                0.9f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1.1f,
                1f
            ),
            ObjectAnimator.ofFloat(view, "rotation", 0f, -3f, -3f, 3f, -3f, 3f, -3f, 3f, -3f, 0f)
        )
    }

    init {
        duration = 1000
    }
}