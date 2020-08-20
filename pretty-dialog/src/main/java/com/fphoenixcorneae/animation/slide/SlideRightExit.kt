package com.fphoenixcorneae.animation.slide

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 右边滑出动效
 */
class SlideRightExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "translationX", 0f, 300f),
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        )
    }
}