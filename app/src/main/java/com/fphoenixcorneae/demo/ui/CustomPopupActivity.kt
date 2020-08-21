package com.fphoenixcorneae.demo.ui

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fphoenixcorneae.animation.bounce.BounceBottomEnter
import com.fphoenixcorneae.animation.bounce.BounceTopEnter
import com.fphoenixcorneae.animation.slide.SlideTopEnter
import com.fphoenixcorneae.animation.slide.SlideBottomExit
import com.fphoenixcorneae.animation.slide.SlideTopExit
import com.fphoenixcorneae.popup.base.BasePopup
import com.fphoenixcorneae.demo.R
import com.fphoenixcorneae.demo.utils.T
import kotlinx.android.synthetic.main.ac_custom_popup.*
import kotlinx.android.synthetic.main.popup_custom.*

class CustomPopupActivity : AppCompatActivity() {

    private val mContext: Context = this
    private lateinit var mQuickCustomPopup: SimpleCustomPop
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_custom_popup)
        mQuickCustomPopup = SimpleCustomPop(mContext)
        //        mQuickCustomPopup.setCanceledOnTouchOutside(false);
        mTvCenter.setOnClickListener {
            mQuickCustomPopup
                .alignCenter(true)
                .anchorView(mTvCenter)
                .gravity(Gravity.BOTTOM)
                .showAnim(SlideTopEnter())
                .dismissAnim(SlideTopExit())
                .offset(0f, -10f)
                .dimEnabled(false)
                .show()
        }
        mTvTopLeft.setOnClickListener {
            mQuickCustomPopup
                .anchorView(mTvTopLeft)
                .gravity(Gravity.BOTTOM)
                .offset(0f, 0f)
                .showAnim(BounceTopEnter())
                .dismissAnim(SlideTopExit())
                .dimEnabled(false)
                .show()
        }
        mTvTopRight.setOnClickListener {
            mQuickCustomPopup
                .anchorView(mTvTopRight)
                .offset(-10f, 5f)
                .gravity(Gravity.BOTTOM)
                .showAnim(BounceTopEnter())
                .dismissAnim(SlideTopExit())
                .dimEnabled(false)
                .show()
        }
        mTvBottomLeft.setOnClickListener {
            mQuickCustomPopup
                .anchorView(mTvBottomLeft)
                .offset(10f, -5f)
                .gravity(Gravity.TOP)
                .showAnim(BounceBottomEnter())
                .dismissAnim(SlideBottomExit())
                .dimEnabled(true)
                .show()
        }
        mTvBottomRight.setOnClickListener {
            mQuickCustomPopup
                .showAnim(null)
                .dismissAnim(null)
                .dimEnabled(true)
                .anchorView(mTvBottomRight)
                .offset(-10f, -5f)
                .dimEnabled(false)
                .gravity(Gravity.TOP)
                .show()
        }
    }

    internal inner class SimpleCustomPop(context: Context) :
        BasePopup<SimpleCustomPop>(context) {
        override fun onCreatePopupView(): View {
            val inflate =
                View.inflate(mContext, R.layout.popup_custom, null)
            return inflate
        }

        override fun setUiBeforeShow() {
            mTvItem1!!.setOnClickListener { T.showShort(mContext, mTvItem1!!.text) }
            mTvItem2!!.setOnClickListener { T.showShort(mContext, mTvItem2!!.text) }
            mTvItem3!!.setOnClickListener { T.showShort(mContext, mTvItem3!!.text) }
            mTvItem4!!.setOnClickListener { T.showShort(mContext, mTvItem4!!.text) }
        }
    }
}