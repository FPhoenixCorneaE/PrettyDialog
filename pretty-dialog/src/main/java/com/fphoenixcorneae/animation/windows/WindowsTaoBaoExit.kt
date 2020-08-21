package com.fphoenixcorneae.animation.windows

import android.animation.ObjectAnimator
import android.view.View
import com.fphoenixcorneae.animation.BaseAnimatorSet

/**
 * @desc 类似iOS淘宝窗口退出动效
 */
class WindowsTaoBaoExit : BaseAnimatorSet() {

    override fun setAnimation(view: View) {
        val displayMetrics = view.context.resources.displayMetrics
        val rotationX =
            ObjectAnimator.ofFloat(view, "rotationX", 10f, 0f).setDuration(150)
        rotationX.startDelay = 200
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f).setDuration(350),
            ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f).setDuration(350),
            ObjectAnimator.ofFloat(view, "rotationX", 0f, 10f).setDuration(200),
            rotationX,
            ObjectAnimator.ofFloat(
                view,
                "translationY",
                -0.1f * displayMetrics.heightPixels,
                0f
            ).setDuration(350)
        )
    }
}