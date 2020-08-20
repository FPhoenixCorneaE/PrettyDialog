package com.fphoenixcorneae.animation.slide

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 底部滑出动效
 */
class SlideBottomExit : BaseAnimatorSet() {
    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "translationY", 0f, 300f),
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        )
    }
}