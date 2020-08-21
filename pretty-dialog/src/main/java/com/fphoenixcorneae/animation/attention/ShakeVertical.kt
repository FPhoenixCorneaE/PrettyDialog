package com.fphoenixcorneae.animation.attention

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.CycleInterpolator
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 垂直震动动效
 */
class ShakeVertical : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationY", -10f, 10f)
        animator.interpolator = CycleInterpolator(5f)
        animatorSet.playTogether(animator)
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                reset(view)
            }
        })
    }

    init {
        duration = 1000
    }
}