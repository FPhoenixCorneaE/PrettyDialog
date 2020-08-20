package com.fphoenixcorneae.demo.utils

import android.content.Context
import com.fphoenixcorneae.animation.BaseAnimatorSet
import com.fphoenixcorneae.animation.attention.*
import com.fphoenixcorneae.animation.blind.BlindEnter
import com.fphoenixcorneae.animation.blind.BlindExit
import com.fphoenixcorneae.animation.bounce.*
import com.fphoenixcorneae.animation.fade.FadeEnter
import com.fphoenixcorneae.animation.fade.FadeExit
import com.fphoenixcorneae.animation.fall.FallEnter
import com.fphoenixcorneae.animation.fall.FallRotateEnter
import com.fphoenixcorneae.animation.flip.*
import com.fphoenixcorneae.animation.flip.FlipHorizontalExit
import com.fphoenixcorneae.animation.flip.FlipVerticalExit
import com.fphoenixcorneae.animation.slide.SlideBottomEnter
import com.fphoenixcorneae.animation.slide.SlideLeftEnter
import com.fphoenixcorneae.animation.slide.SlideRightEnter
import com.fphoenixcorneae.animation.slide.SlideTopEnter
import com.fphoenixcorneae.animation.slide.SlideBottomExit
import com.fphoenixcorneae.animation.slide.SlideLeftExit
import com.fphoenixcorneae.animation.slide.SlideRightExit
import com.fphoenixcorneae.animation.slide.SlideTopExit
import com.fphoenixcorneae.animation.slit.SlitEnter
import com.fphoenixcorneae.animation.slit.SlitExit
import com.fphoenixcorneae.animation.zoom.*
import com.fphoenixcorneae.dialog.ActionSheetDialog
import com.fphoenixcorneae.demo.ui.DialogHomeActivity
import java.util.*

object DiaogAnimChoose {
    fun showAnim(context: Context) {
        val cs = arrayOf<Class<*>>(
            BounceEnter::class.java,  //
            BounceTopEnter::class.java,  //
            BounceBottomEnter::class.java,  //
            BounceLeftEnter::class.java,  //
            BounceRightEnter::class.java,  //
            FlipHorizontalEnter::class.java,  //
            FlipHorizontalSwingEnter::class.java,  //
            FlipVerticalEnter::class.java,  //
            FlipVerticalSwingEnter::class.java,  //
            FlipTopEnter::class.java,  //
            FlipBottomEnter::class.java,  //
            FlipLeftEnter::class.java,  //
            FlipRightEnter::class.java,  //
            FadeEnter::class.java,  //
            BlindEnter::class.java,
            FallEnter::class.java,  //
            FallRotateEnter::class.java,  //
            SlideTopEnter::class.java,  //
            SlideBottomEnter::class.java,  //
            SlideLeftEnter::class.java,  //
            SlideRightEnter::class.java,  //
            ZoomInEnter::class.java,  //
            ZoomOutEnter::class.java,  //
            ZoomInTopEnter::class.java,  //
            ZoomInBottomEnter::class.java,  //
            ZoomInLeftEnter::class.java,  //
            ZoomInRightEnter::class.java,  //
            NewsPaper::class.java,  //
            Flash::class.java,  //
            ShakeHorizontal::class.java,  //
            ShakeVertical::class.java,  //
            Jelly::class.java,  //
            RubberBand::class.java,  //
            Swing::class.java,  //
            Tada::class.java,  //
            SlitEnter::class.java
        )
        val itemList = ArrayList<String>()
        for (c in cs) {
            itemList.add(c.simpleName)
        }
        val contents = arrayOfNulls<String>(itemList.size)
        val dialog = ActionSheetDialog(
            context,
            itemList.toArray(contents), null
        )
        dialog.title("使用内置show动画设置对话框显示动画\r\n指定对话框将显示效果") //
            .titleTextSize_SP(14.5f) //
            .layoutAnimation(null) //
            .cancelText("cancel")
            .show()
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnOperationItemClickL { parent, view, position, id ->
            try {
                val animType = contents[position]
                (context as DialogHomeActivity).setBasIn(
                    cs[position].newInstance() as BaseAnimatorSet
                )
                T.showShort(context, animType + "设置成功")
                dialog.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun dissmissAnim(context: Context) {
        val cs = arrayOf<Class<*>>(
            FlipHorizontalExit::class.java,  //
            FlipVerticalExit::class.java,  //
            FadeExit::class.java,  //
            SlideTopExit::class.java,  //
            SlideBottomExit::class.java,  //
            SlideLeftExit::class.java,  //
            SlideRightExit::class.java,  //
            ZoomOutExit::class.java,  //
            ZoomOutTopExit::class.java,  //
            ZoomOutBottomExit::class.java,  //
            ZoomOutLeftExit::class.java,  //
            ZoomOutRightExit::class.java,  //
            ZoomInExit::class.java,
            SlitExit::class.java,
            BlindExit::class.java
        )
        val itemList = ArrayList<String>()
        for (c in cs) {
            itemList.add(c.simpleName)
        }
        val contents = arrayOfNulls<String>(itemList.size)
        val dialog = ActionSheetDialog(
            context,  //
            itemList.toArray(contents), null
        )
        dialog.title("使用内置dismiss动画设置对话框消失动画\r\n指定对话框将消失效果") //
            .titleTextSize_SP(14.5f) //
            .cancelText("cancel")
            .show()
        dialog.setOnOperationItemClickL { parent, view, position, id ->
            try {
                val animType = contents[position]
                (context as DialogHomeActivity).setBasOut(
                    cs[position].newInstance() as BaseAnimatorSet
                )
                T.showShort(context, animType + "设置成功")
                dialog.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}