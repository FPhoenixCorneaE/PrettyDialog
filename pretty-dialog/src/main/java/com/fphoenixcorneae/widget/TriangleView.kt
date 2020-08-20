package com.fphoenixcorneae.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.Gravity
import android.view.View

class TriangleView : View {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPath = Path()
    private var mColor = 0

    /** 三角形朝向  */
    private var mGravity = 0

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    var color: Int
        get() = mColor
        set(color) {
            mColor = color
            invalidate()
        }

    var gravity: Int
        get() = mGravity
        set(gravity) {
            mGravity = gravity
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width
        val height = height
        mPaint.color = mColor
        mPath.reset()
        if (mGravity == Gravity.TOP) {
            //三角形朝上
            mPath.moveTo(width / 2.toFloat(), 0f)
            mPath.lineTo(0f, height.toFloat())
            mPath.lineTo(width.toFloat(), height.toFloat())
            mPath.close()
        } else if (mGravity == Gravity.BOTTOM) {
            //三角形朝下
            mPath.moveTo(0f, 0f)
            mPath.lineTo(width.toFloat(), 0f)
            mPath.lineTo(width / 2.toFloat(), height.toFloat())
            mPath.close()
        }
        canvas.drawPath(mPath, mPaint)
    }
}