package com.fphoenixcorneae.demo.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fphoenixcorneae.animation.bounce.BounceRightEnter
import com.fphoenixcorneae.animation.slide.SlideBottomEnter
import com.fphoenixcorneae.animation.slide.SlideBottomExit
import com.fphoenixcorneae.animation.slide.SlideLeftExit
import com.fphoenixcorneae.popup.BubblePopup
import com.fphoenixcorneae.demo.R
import com.fphoenixcorneae.demo.extra.CustomBubblePopup
import com.fphoenixcorneae.demo.utils.T
import kotlinx.android.synthetic.main.ac_bubble_popup.*

class BubblePopupActivity : AppCompatActivity() {

    private val mContext: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_bubble_popup)
        mTvCenter.setOnClickListener {
            val inflate =
                View.inflate(mContext, R.layout.popup_bubble_image, null)
            val bubblePopup = BubblePopup(mContext, inflate)
            bubblePopup.anchorView(mTvCenter)
                .showAnim(BounceRightEnter())
                .dismissAnim(SlideLeftExit())
                .autoDismiss(true)
                .show()
        }
        mTvTopLeft.setOnClickListener {
            val inflate =
                View.inflate(mContext, R.layout.popup_bubble_text, null)
            val tv = inflate.findViewById<View>(R.id.tv_bubble) as TextView
            val bubblePopup = BubblePopup(mContext, inflate)
            tv.text = "最美的不是下雨天,是曾与你躲过雨的屋檐~"
            bubblePopup.anchorView(mTvTopLeft)
                .gravity(Gravity.BOTTOM)
                .show()
            tv.setOnClickListener { T.showShort(mContext, "tv_bubble") }
        }
        mTvTopRight.setOnClickListener {
            val customBubblePopup = CustomBubblePopup(mContext)
            //        customBubblePopup.setCanceledOnTouchOutside(false);
            customBubblePopup
                .gravity(Gravity.BOTTOM)
                ?.anchorView(mTvTopRight)
                ?.triangleWidth(20f)
                ?.triangleHeight(10f)
                ?.showAnim(null)
                ?.dismissAnim(null)
                ?.show()
        }
        mTvBottomLeft.setOnClickListener {
            val inflate =
                View.inflate(mContext, R.layout.popup_bubble_text, null)
            BubblePopup(mContext, inflate)
                .anchorView(mTvBottomLeft)
                .showAnim(null)
                .dismissAnim(null)
                .show()
        }
        mTvBottomRight.setOnClickListener {
            val inflate =
                View.inflate(mContext, R.layout.popup_bubble_image, null)
            BubblePopup(mContext, inflate).anchorView(mTvBottomRight)
                .bubbleColor(Color.parseColor("#8BC34A"))
                .showAnim(SlideBottomEnter())
                .dismissAnim(SlideBottomExit())
                .show()
        }
    }
}