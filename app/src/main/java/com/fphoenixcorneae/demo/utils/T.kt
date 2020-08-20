package com.fphoenixcorneae.demo.utils

import android.content.Context
import android.widget.Toast

/**
 * Toast统一管理类
 */
object T {
    private var toast: Toast? = null

    /**
     * 短时间显示Toast
     */
    fun showShort(context: Context?, message: CharSequence?) {
        if (null == toast) {
            toast =
                Toast.makeText(context, message, Toast.LENGTH_SHORT)
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast!!.setText(message)
        }
        toast!!.show()
    }

    /**
     * 短时间显示Toast
     */
    fun showShort(context: Context?, message: Int) {
        if (null == toast) {
            toast =
                Toast.makeText(context, message, Toast.LENGTH_SHORT)
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast!!.setText(message)
        }
        toast!!.show()
    }

    /**
     * 长时间显示Toast
     */
    fun showLong(context: Context?, message: CharSequence?) {
        if (null == toast) {
            toast =
                Toast.makeText(context, message, Toast.LENGTH_LONG)
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast!!.setText(message)
        }
        toast!!.show()
    }

    /**
     * 长时间显示Toast
     */
    fun showLong(context: Context?, message: Int) {
        if (null == toast) {
            toast =
                Toast.makeText(context, message, Toast.LENGTH_LONG)
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast!!.setText(message)
        }
        toast!!.show()
    }

    /**
     * 自定义显示Toast时间
     */
    fun show(
        context: Context?,
        message: CharSequence?,
        duration: Int
    ) {
        if (null == toast) {
            toast = Toast.makeText(context, message, duration)
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast!!.setText(message)
        }
        toast!!.show()
    }

    /**
     * 自定义显示Toast时间
     */
    fun show(context: Context?, message: Int, duration: Int) {
        if (null == toast) {
            toast = Toast.makeText(context, message, duration)
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast!!.setText(message)
        }
        toast!!.show()
    }

    /** Hide the toast, if any.  */
    fun hideToast() {
        if (null != toast) {
            toast!!.cancel()
        }
    }
}