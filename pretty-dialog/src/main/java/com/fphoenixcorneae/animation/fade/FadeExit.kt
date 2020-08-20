package com.fphoenixcorneae.animation.fade

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 淡出动效
 */
class FadeExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f).setDuration(duration)
        )
    }
}