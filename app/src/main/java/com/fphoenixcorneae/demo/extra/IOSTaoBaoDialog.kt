package com.fphoenixcorneae.demo.extra

import android.content.Context
import android.view.View
import com.fphoenixcorneae.animation.windows.WindowsTaoBaoEnter
import com.fphoenixcorneae.animation.windows.WindowsTaoBaoExit
import com.fphoenixcorneae.demo.R
import com.fphoenixcorneae.demo.utils.T
import com.fphoenixcorneae.dialog.base.BottomBaseDialog
import kotlinx.android.synthetic.main.dialog_ios_taobao.*

class IOSTaoBaoDialog : BottomBaseDialog<IOSTaoBaoDialog> {

    constructor(context: Context, animateView: View?) : super(
        context,
        animateView
    ) {
    }

    constructor(context: Context) : super(context) {}

    override fun onCreateView(): View {
        return View.inflate(mContext, R.layout.dialog_ios_taobao, null)
    }

    override fun onStart() {
        super.onStart()
        showAnim(null)
        dismissAnim(null)
        windowsEnterAnim(WindowsTaoBaoEnter())
        windowsExitAnim(WindowsTaoBaoExit())
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