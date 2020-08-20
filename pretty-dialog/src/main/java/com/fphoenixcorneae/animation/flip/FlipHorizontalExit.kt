package com.fphoenixcorneae.animation.flip

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

class FlipHorizontalExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "rotationY", 0f, 90f),
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        )
    }
}