package com.fphoenixcorneae.popup

import android.content.Context
import android.view.View
import com.fphoenixcorneae.popup.base.BaseBubblePopup

/**
 * Use dialog to realize bubble style popup(利用Dialog实现泡泡样式的弹窗)
 * thanks https://github.com/michaelye/EasyDialog
 */
class BubblePopup(context: Context, wrappedView: View?) :
    BaseBubblePopup<BubblePopup>(context, wrappedView) {
    override fun onCreateBubbleView(): View? {
        return null
    }
}