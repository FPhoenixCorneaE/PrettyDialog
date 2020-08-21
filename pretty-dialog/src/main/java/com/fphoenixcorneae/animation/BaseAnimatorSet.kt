package com.fphoenixcorneae.animation

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.view.View
import android.view.animation.Interpolator

/**
 * @desc Base动画合集
 */
abstract class BaseAnimatorSet {
    /**
     * 动画时长,系统默认250
     */
    protected var duration: Long = 500
    protected var animatorSet = AnimatorSet()
    private var interpolator: Interpolator? = null
    private var delay: Long = 0
    private var listener: AnimatorListener? = null

    abstract fun setAnimation(view: View)

    private fun start(view: View) {
        reset(view)
        setAnimation(view)
        animatorSet.let {
            it.duration = duration
            if (interpolator != null) {
                it.interpolator = interpolator
            }
            if (delay > 0) {
                it.startDelay = delay
            }
            it.addListener(object : AnimatorListener {
                override fun onAnimationStart(animator: Animator) {
                    listener?.onAnimationStart(animator)
                }

                override fun onAnimationRepeat(animator: Animator) {
                    listener?.onAnimationRepeat(animator)
                }

                override fun onAnimationEnd(animator: Animator) {
                    listener?.onAnimationEnd(animator)
                }

                override fun onAnimationCancel(animator: Animator) {
                    listener?.onAnimationCancel(animator)
                }
            })
            it.start()
        }
    }

    /**
     * 设置动画时长
     */
    fun duration(duration: Long): BaseAnimatorSet {
        this.duration = duration
        return this
    }

    /**
     * 设置动画时长
     */
    fun delay(delay: Long): BaseAnimatorSet {
        this.delay = delay
        return this
    }

    /**
     * 设置动画插补器
     */
    fun interpolator(interpolator: Interpolator?): BaseAnimatorSet {
        this.interpolator = interpolator
        return this
    }

    /**
     * 动画监听
     */
    fun listener(listener: AnimatorListener?): BaseAnimatorSet {
        this.listener = listener
        return this
    }

    /**
     * 在View上执行动画
     */
    fun playOn(view: View) {
        start(view)
    }

    companion object {

        fun reset(view: View) {
            view.post {
                view.apply {
                    alpha = 1f
                    scaleX = 1f
                    scaleY = 1f
                    translationX = 0f
                    translationY = 0f
                    rotation = 0f
                    rotationY = 0f
                    rotationX = 0f
                    pivotX = view.measuredWidth / 2f
                    pivotY = view.measuredHeight / 2f
                }
            }
        }
    }
}