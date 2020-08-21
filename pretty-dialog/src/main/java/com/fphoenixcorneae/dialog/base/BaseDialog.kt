package com.fphoenixcorneae.dialog.base

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.fphoenixcorneae.animation.BaseAnimatorSet
import com.fphoenixcorneae.animation.windows.WindowsScaleEnter
import com.fphoenixcorneae.animation.windows.WindowsScaleExit
import com.fphoenixcorneae.utils.StatusBarUtils
import com.github.mmin18.widget.RealtimeBlurView

abstract class BaseDialog<T : BaseDialog<T>>(
    context: Context
) : Dialog(context) {
    /**
     * mTag(日志)
     */
    protected var mTag: String

    /**
     * mContext(上下文)
     */
    protected var mContext: Context

    /**
     * (DisplayMetrics)设备密度
     */
    protected lateinit var mDisplayMetrics: DisplayMetrics

    /**
     * enable dismiss outside dialog(设置点击对话框以外区域,是否dismiss)
     */
    protected var mCancel = false

    /**
     * dialog width scale(宽度比例)
     */
    protected var mWidthScale = 1f

    /**
     * dialog height scale(高度比例)
     */
    protected var mHeightScale = 0f

    /**
     * mShowAnim(对话框显示动画)
     */
    private var mShowAnim: BaseAnimatorSet? = null

    /**
     * mDismissAnim(对话框消失动画)
     */
    private var mDismissAnim: BaseAnimatorSet? = null

    /**
     * top container(最上层容器)
     */
    protected lateinit var mFlTop: FrameLayout

    /**
     * blurred background
     */
    private var mIsBlurEnabled = false

    /**
     * container to control dialog height(用于控制对话框高度)
     */
    protected lateinit var mLlControlHeight: LinearLayout

    /**
     * get actual created view(获取实际创建的View)
     */
    /**
     * the child of mLlControlHeight you create.(创建出来的mLlControlHeight的直接子View)
     */
    protected var mOnCreateView: View? = null

    /**
     * is mShowAnim running(显示动画是否正在执行)
     */
    private var mIsShowAnim = false

    /**
     * is DismissAnim running(消失动画是否正在执行)
     */
    private var mIsDismissAnim = false

    /**
     * max height(最大高度)
     */
    protected var mMaxHeight = 0f

    /**
     * show Dialog as PopupWindow(像PopupWindow一样展示Dialog)
     */
    private var mIsPopupStyle = false

    /**
     * automatic dismiss dialog after given delay(在给定时间后,自动消失)
     */
    private var mAutoDismiss = false

    /**
     * delay (milliseconds) to dismiss dialog(对话框消失延时时间,毫秒值)
     */
    private var mAutoDismissDelay: Long = 1500
    private val mHandler = Handler(Looper.getMainLooper())

    /**
     * displays the rootView of the dialog's activity(显示dialog的activity的rootView)
     */
    private var mAnimatedView: View? = null
    private var mWindowsEnterAnim: BaseAnimatorSet? = WindowsScaleEnter()
    private var mWindowsExitAnim: BaseAnimatorSet? = WindowsScaleExit()

    /**
     * 对话框显示动画
     */
    private var mInnerShowAnim: Animation? = null
    private var mInnerDismissAnim: Animation? = null
    private var mInnerAnimDuration: Long = 350
    private var mIsInnerShowAnim = false
    private var mIsInnerDismissAnim = false

    constructor(context: Context, isPopupStyle: Boolean) : this(context) {
        mIsPopupStyle = isPopupStyle
    }

    /**
     * set dialog theme(设置对话框主题)
     */
    private fun setDialogTheme() {
        requestWindowFeature(Window.FEATURE_NO_TITLE) // android:windowNoTitle
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // android:windowBackground
            addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND) // android:backgroundDimEnabled默认是true的
        }
    }

    /**
     * inflate layout for dialog ui and return (填充对话框所需要的布局并返回)
     * <pre>
     * public View onCreateView() {
     * View inflate = View.inflate(mContext, R.layout.dialog_share, null);
     * return inflate;
     * }
    </pre> *
     */
    abstract fun onCreateView(): View?

    open fun onViewCreated(inflate: View?) {}

    /**
     * set Ui data or logic operation before attached window(在对话框显示之前,设置界面数据或者逻辑)
     */
    abstract fun setUiBeforeShow()

    /**
     * set dialog gravity
     */
    protected open val gravity: Int
        get() = Gravity.CENTER

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(mTag, "onCreate")
        mDisplayMetrics = mContext.resources.displayMetrics
        mMaxHeight = mDisplayMetrics.heightPixels - StatusBarUtils.getHeight(mContext).toFloat()
//         mMaxHeight = mDisplayMetrics.heightPixels.toFloat()
        mFlTop = FrameLayout(mContext)
        mFlTop.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        if (mIsBlurEnabled) {
            // 高斯模糊背景
            val realtimeBlurView = RealtimeBlurView(mContext, null)
            realtimeBlurView.setOverlayColor(Color.argb(22, 0, 0, 0))
            mFlTop.addView(
                realtimeBlurView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
        }
        mLlControlHeight = LinearLayout(mContext)
        mLlControlHeight.orientation = LinearLayout.VERTICAL
        mOnCreateView = onCreateView()
        mLlControlHeight.addView(mOnCreateView)
        mFlTop.addView(mLlControlHeight)
        onViewCreated(mOnCreateView)
        if (mIsPopupStyle) {
            setContentView(
                mFlTop,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
        } else {
            setContentView(
                mFlTop,
                ViewGroup.LayoutParams(
                    mDisplayMetrics.widthPixels,
                    mMaxHeight.toInt()
                )
            )
        }
        mFlTop.setOnClickListener {
            if (mCancel) {
                dismiss()
            }
        }
        mOnCreateView!!.isClickable = true
    }

    /**
     * when dialog attached to window,set dialog width and height and show anim
     * (当dialog依附在window上,设置对话框宽高以及显示动画)
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(mTag, "onAttachedToWindow")
        setUiBeforeShow()
        val width: Int = if (mWidthScale == 0f) {
            ViewGroup.LayoutParams.WRAP_CONTENT
        } else {
            (mDisplayMetrics.widthPixels * mWidthScale).toInt()
        }
        val height: Int = when (mHeightScale) {
            0f -> {
                ViewGroup.LayoutParams.WRAP_CONTENT
            }
            1f -> {
                //            height = ViewGroup.LayoutParams.MATCH_PARENT;
                mMaxHeight.toInt()
            }
            else -> {
                (mMaxHeight * mHeightScale).toInt()
            }
        }
        val layoutParams = FrameLayout.LayoutParams(width, height)
        layoutParams.gravity = gravity
        mLlControlHeight.layoutParams = layoutParams
        if (mShowAnim != null) {
            mShowAnim!!.listener(object : AnimatorListener {
                override fun onAnimationStart(animator: Animator) {
                    mIsShowAnim = true
                }

                override fun onAnimationRepeat(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    mIsShowAnim = false
                    delayDismiss()
                }

                override fun onAnimationCancel(animator: Animator) {
                    mIsShowAnim = false
                }
            }).playOn(mLlControlHeight)
        } else {
            BaseAnimatorSet.reset(mLlControlHeight)
            delayDismiss()
        }

        // show dialog and mAnimateView with inner show animation(设置dialog和animateView显示动画)
        if (mInnerShowAnim != null) {
            mInnerShowAnim!!.duration = mInnerAnimDuration
            mInnerShowAnim!!.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    mIsInnerShowAnim = true
                }

                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    mIsInnerShowAnim = false
                }
            })
            mLlControlHeight.startAnimation(mInnerShowAnim)
        }
        if (mAnimatedView != null) {
            mWindowsEnterAnim?.duration(mInnerAnimDuration)?.playOn(mAnimatedView!!)
        }
    }

    final override fun setCanceledOnTouchOutside(cancel: Boolean) {
        mCancel = cancel
        super.setCanceledOnTouchOutside(cancel)
    }

    override fun show() {
        Log.d(mTag, "show")
        super.show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(mTag, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(mTag, "onStop")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(mTag, "onDetachedFromWindow")
    }

    override fun dismiss() {
        Log.d(mTag, "dismiss")
        if (mDismissAnim != null) {
            mDismissAnim!!.listener(object : AnimatorListener {
                override fun onAnimationStart(animator: Animator) {
                    mIsDismissAnim = true
                }

                override fun onAnimationRepeat(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    mIsDismissAnim = false
                    superDismiss()
                }

                override fun onAnimationCancel(animator: Animator) {
                    mIsDismissAnim = false
                    superDismiss()
                }
            }).playOn(mLlControlHeight)
        } else if (mInnerDismissAnim != null) {
            mInnerDismissAnim!!.duration = mInnerAnimDuration
            mInnerDismissAnim!!.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    mIsInnerDismissAnim = true
                }

                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    mIsInnerDismissAnim = false
                    superDismiss()
                }
            })
            mLlControlHeight.startAnimation(mInnerDismissAnim)
        } else {
            superDismiss()
        }
        if (mAnimatedView != null) {
            mWindowsExitAnim?.duration(mInnerAnimDuration)?.playOn(mAnimatedView!!)
        }
    }

    /**
     * dismiss without anim(无动画dismiss)
     */
    fun superDismiss() {
        super.dismiss()
    }

    /**
     * dialog anim by styles(动画弹出对话框,style动画资源)
     */
    fun show(animStyle: Int) {
        val window = window
        window?.setWindowAnimations(animStyle)
        show()
    }

    /**
     * show at location only valid for mIsPopupStyle true(指定位置显示,只对isPopupStyle为true有效)
     */
    fun showAtLocation(gravity: Int, x: Int, y: Int) {
        if (mIsPopupStyle) {
            val window = window
            val params = window?.attributes
            window?.setGravity(gravity)
            params?.x = x
            params?.y = y
        }
        show()
    }

    /**
     * show at location only valid for mIsPopupStyle true(指定位置显示,只对isPopupStyle为true有效)
     */
    fun showAtLocation(x: Int, y: Int) {
        val gravity = Gravity.START or Gravity.TOP //Left Top (坐标原点为右上角)
        showAtLocation(gravity, x, y)
    }

    /**
     * set window dim or not(设置背景是否昏暗)
     */
    fun dimEnabled(isDimEnabled: Boolean): T {
        if (isDimEnabled) {
            window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        } else {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
        return this as T
    }

    /**
     * set window blur or not(设置背景是否模糊)
     */
    fun blurEnabled(isBlurEnabled: Boolean): T {
        mIsBlurEnabled = isBlurEnabled
        return this as T
    }

    /**
     * set dialog width scale:0-1(设置对话框宽度,占屏幕宽的比例0-1)
     */
    fun widthScale(widthScale: Float): T {
        mWidthScale = widthScale
        return this as T
    }

    /**
     * set dialog height scale:0-1(设置对话框高度,占屏幕宽的比例0-1)
     */
    fun heightScale(heightScale: Float): T {
        mHeightScale = heightScale
        return this as T
    }

    /**
     * set show anim(设置显示的动画)
     */
    fun showAnim(showAnim: BaseAnimatorSet?): T {
        mShowAnim = showAnim
        return this as T
    }

    /**
     * set dismiss anim(设置隐藏的动画)
     */
    fun dismissAnim(dismissAnim: BaseAnimatorSet?): T {
        mDismissAnim = dismissAnim
        return this as T
    }

    /**
     * automatic dismiss dialog after given delay(在给定时间后,自动消失)
     */
    fun autoDismiss(autoDismiss: Boolean): T {
        mAutoDismiss = autoDismiss
        return this as T
    }

    /**
     * set delay (milliseconds) to dismiss dialog(对话框消失延时时间,毫秒值)
     */
    fun autoDismissDelay(autoDismissDelay: Long): T {
        mAutoDismissDelay = autoDismissDelay
        return this as T
    }

    private fun delayDismiss() {
        if (mAutoDismiss && mAutoDismissDelay > 0) {
            mHandler.postDelayed({ dismiss() }, mAutoDismissDelay)
        }
    }

    /**
     * set animatedView(显示dialog的activity的rootView)
     */
    fun animatedView(animatedView: View?): T {
        mAnimatedView = animatedView
        return this as T
    }

    /** set enter animation of animateView(设置animateView显示动画)  */
    fun windowsEnterAnim(windowsEnterAnim: BaseAnimatorSet?): T {
        mWindowsEnterAnim = windowsEnterAnim
        return this as T
    }

    /** set exit animation of animateView(设置animateView消失动画)  */
    fun windowsExitAnim(windowsExitAnim: BaseAnimatorSet?): T {
        mWindowsExitAnim = windowsExitAnim
        return this as T
    }

    /** set inner animation of dialog(设置dialog内置显示动画)  */
    fun innerShowAnim(innerShowAnim: Animation?): T {
        mInnerShowAnim = innerShowAnim
        return this as T
    }

    /** set dismiss animation of dialog(设置dialog内置消失动画)  */
    fun innerDismissAnim(innerDismissAnim: Animation?): T {
        mInnerDismissAnim = innerDismissAnim
        return this as T
    }

    /** set duration for inner animation of dialog and animateView(设置dialog内置动画和animateView动画时长)  */
    fun innerAnimDuration(innerAnimDuration: Long): T {
        mInnerAnimDuration = innerAnimDuration
        return this as T
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return if (mIsDismissAnim || mIsShowAnim || mAutoDismiss || mIsInnerDismissAnim || mIsInnerShowAnim) {
            true
        } else super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        if (mIsDismissAnim || mIsShowAnim || mAutoDismiss || mIsInnerDismissAnim || mIsInnerShowAnim) {
            return
        }
        super.onBackPressed()
    }

    /**
     * dp to px
     */
    protected fun dp2px(dp: Float): Int {
        val scale = mContext.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    /**
     * method execute order:
     * show:constructor---show---onCreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     */
    init {
        setDialogTheme()
        mContext = context
        mTag = javaClass.simpleName
        setCanceledOnTouchOutside(true)
        Log.d(mTag, "constructor")
    }
}