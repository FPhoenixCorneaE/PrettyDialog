package com.fphoenixcorneae.demo.extra

import android.content.Context
import android.view.View
import com.fphoenixcorneae.animation.flip.FlipVerticalSwingEnter
import com.fphoenixcorneae.dialog.base.BottomBaseDialog
import com.fphoenixcorneae.demo.R
import com.fphoenixcorneae.demo.utils.T
import kotlinx.android.synthetic.main.dialog_share.*

class ShareBottomDialog : BottomBaseDialog<ShareBottomDialog> {

    constructor(context: Context, animateView: View?) : super(
        context,
        animateView
    ) {
    }

    constructor(context: Context) : super(context) {}

    override fun onCreateView(): View {
        showAnim(FlipVerticalSwingEnter())
        dismissAnim(null)
        val inflate =
            View.inflate(mContext, R.layout.dialog_share, null)
        return inflate
    }

    override fun setUiBeforeShow() {
        mLlWechatFriendCircle!!.setOnClickListener {
            T.showShort(mContext, "朋友圈")
            dismiss()
        }
        mLlWechatFriend!!.setOnClickListener {
            T.showShort(mContext, "微信")
            dismiss()
        }
        mLlQq!!.setOnClickListener {
            T.showShort(mContext, "QQ")
            dismiss()
        }
        mLlSms!!.setOnClickListener {
            T.showShort(mContext, "短信")
            dismiss()
        }
    }
}