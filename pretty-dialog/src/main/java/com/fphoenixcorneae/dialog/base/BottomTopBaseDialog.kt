package com.fphoenixcorneae.dialog.base

import android.content.Context

abstract class BottomTopBaseDialog<T : BottomTopBaseDialog<T>>(
    context: Context
) : BaseDialog<T>(context) {
    protected var mLeft = 0
    protected var mTop = 0
    protected var mRight = 0
    protected var mBottom = 0

    fun padding(left: Int, top: Int, right: Int, bottom: Int): T {
        mLeft = left
        mTop = top
        mRight = right
        mBottom = bottom
        return this as T
    }
}