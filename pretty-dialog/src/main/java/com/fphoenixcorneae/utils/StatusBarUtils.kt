package com.fphoenixcorneae.utils

import android.content.Context
import android.os.Build
import android.text.TextUtils

/**
 * StatusBar Utils handle with special FlymeOS4.x/Android4.4.4
 * (状态栏工具,处理魅族FlymeOS4.x/Android4.4.4)
 */
object StatusBarUtils {
    fun getHeight(context: Context): Int {
        var statusBarHeight = 0
        val resourceId =
            context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
        }
        return if (isFlymeOs4x) {
            2 * statusBarHeight
        } else statusBarHeight
    }

    val isFlymeOs4x: Boolean
        get() {
            val sysVersion = Build.VERSION.RELEASE
            if ("4.4.4" == sysVersion) {
                val sysIncrement = Build.VERSION.INCREMENTAL
                val displayId = Build.DISPLAY
                return if (!TextUtils.isEmpty(sysIncrement)) {
                    sysIncrement.contains("Flyme_OS_4")
                } else {
                    displayId.contains("Flyme OS 4")
                }
            }
            return false
        }
}