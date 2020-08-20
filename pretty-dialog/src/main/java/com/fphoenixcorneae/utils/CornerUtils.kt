package com.fphoenixcorneae.utils

import android.R
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable

/**
 * Utils to get corner drawable
 */
object CornerUtils {
    fun cornerDrawable(bgColor: Int, cornerRadius: Float): Drawable {
        return GradientDrawable().apply {
            this.cornerRadius = cornerRadius
            setColor(bgColor)
        }
    }

    fun cornerDrawable(
        bgColor: Int,
        cornerRadii: FloatArray?,
        borderWidth: Int = 0,
        borderColor: Int = 0
    ): Drawable {
        return GradientDrawable().apply {
            this.cornerRadii = cornerRadii
            setStroke(borderWidth, borderColor)
            setColor(bgColor)
        }
    }

    /**
     * set btn selector with corner drawable for special position
     */
    fun btnSelector(
        radius: Float,
        normalColor: Int,
        pressColor: Int,
        position: Int
    ): StateListDrawable {
        return StateListDrawable().apply {
            val (normal, pressed) = when (position) {
                0 -> {
                    // left btn : bottom left radius
                    Pair(
                        cornerDrawable(
                            normalColor,
                            floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, radius, radius)
                        ),
                        cornerDrawable(
                            pressColor,
                            floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, radius, radius)
                        )
                    )
                }
                1 -> {
                    // right btn : bottom right radius
                    Pair(
                        cornerDrawable(
                            normalColor,
                            floatArrayOf(0f, 0f, 0f, 0f, radius, radius, 0f, 0f)
                        ),
                        cornerDrawable(
                            pressColor,
                            floatArrayOf(0f, 0f, 0f, 0f, radius, radius, 0f, 0f)
                        )
                    )
                }
                -1 -> {
                    // only one btn
                    Pair(
                        cornerDrawable(
                            normalColor,
                            floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
                        ),
                        cornerDrawable(
                            pressColor,
                            floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
                        )
                    )
                }
                else -> {
                    // for material dialog
                    Pair(
                        cornerDrawable(normalColor, radius),
                        cornerDrawable(pressColor, radius)
                    )
                }
            }
            addState(intArrayOf(-R.attr.state_pressed), normal)
            addState(intArrayOf(R.attr.state_pressed), pressed)
        }
    }

    /**
     * set ListView item selector with corner drawable for the last position
     * (ListView的item点击效果,只处理最后一项圆角处理)
     */
    fun listItemSelector(
        radius: Float,
        normalColor: Int,
        pressColor: Int,
        isLastPosition: Boolean
    ): StateListDrawable {
        return StateListDrawable().apply {
            val (normal, pressed) =
                when {
                    !isLastPosition -> Pair(
                        ColorDrawable(normalColor),
                        ColorDrawable(pressColor)
                    )
                    else -> Pair(
                        cornerDrawable(
                            normalColor,
                            floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
                        ),
                        cornerDrawable(
                            pressColor,
                            floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
                        )
                    )
                }
            addState(intArrayOf(-R.attr.state_pressed), normal)
            addState(intArrayOf(R.attr.state_pressed), pressed)
        }
    }

    /**
     * set ListView item selector with corner drawable for the first and the last position
     * (ListView的item点击效果,第一项和最后一项圆角处理)
     */
    fun listItemSelector(
        radius: Float, normalColor: Int, pressColor: Int, itemTotalSize: Int,
        itemPosition: Int
    ): StateListDrawable {
        return StateListDrawable().apply {
            val (normal, pressed) =
                when {
                    // 只有一项
                    itemPosition == 0 && itemPosition == itemTotalSize - 1 -> Pair(
                        cornerDrawable(
                            normalColor, floatArrayOf(
                                radius, radius, radius, radius, radius, radius, radius, radius
                            )
                        ),
                        cornerDrawable(
                            pressColor, floatArrayOf(
                                radius, radius, radius, radius, radius, radius, radius, radius
                            )
                        )
                    )
                    itemPosition == 0 -> Pair(
                        cornerDrawable(
                            normalColor,
                            floatArrayOf(radius, radius, radius, radius, 0f, 0f, 0f, 0f)
                        ),
                        cornerDrawable(
                            pressColor,
                            floatArrayOf(radius, radius, radius, radius, 0f, 0f, 0f, 0f)
                        )
                    )
                    itemPosition == itemTotalSize - 1 -> Pair(
                        cornerDrawable(
                            normalColor,
                            floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
                        ),
                        cornerDrawable(
                            pressColor,
                            floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
                        )
                    )
                    else -> Pair(
                        ColorDrawable(normalColor),
                        ColorDrawable(pressColor)
                    )
                }
            addState(intArrayOf(-R.attr.state_pressed), normal)
            addState(intArrayOf(R.attr.state_pressed), pressed)
        }
    }
}