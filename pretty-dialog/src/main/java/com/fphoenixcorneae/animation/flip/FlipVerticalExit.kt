package com.fphoenixcorneae.animation.flip

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

class FlipVerticalExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "rotationX", 0f, 90f),
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        )
    }
}