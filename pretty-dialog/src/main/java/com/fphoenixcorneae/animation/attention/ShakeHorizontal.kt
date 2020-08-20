package com.fphoenixcorneae.animation.attention

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.CycleInterpolator
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 水平震动动效
 */
class ShakeHorizontal : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationX", -10f, 10f)
        animator.interpolator = CycleInterpolator(5f)
        animatorSet.playTogether(animator)
    }

    init {
        duration = 1000
    }
}