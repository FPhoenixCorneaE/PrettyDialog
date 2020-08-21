package com.fphoenixcorneae.demo.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ExpandableListView
import android.widget.ExpandableListView.OnChildClickListener
import androidx.appcompat.app.AppCompatActivity
import com.fphoenixcorneae.animation.BaseAnimatorSet
import com.fphoenixcorneae.animation.bounce.BounceTopEnter
import com.fphoenixcorneae.animation.slide.SlideBottomExit
import com.fphoenixcorneae.animation.windows.*
import com.fphoenixcorneae.demo.R
import com.fphoenixcorneae.demo.adapter.HomeAdapter
import com.fphoenixcorneae.demo.adapter.TestAdapter
import com.fphoenixcorneae.demo.extra.CustomBaseDialog
import com.fphoenixcorneae.demo.extra.IOSTaoBaoDialog
import com.fphoenixcorneae.demo.extra.ShareBottomDialog
import com.fphoenixcorneae.demo.extra.ShareTopDialog
import com.fphoenixcorneae.demo.utils.DiaogAnimChoose
import com.fphoenixcorneae.demo.utils.T
import com.fphoenixcorneae.dialog.ActionSheetDialog
import com.fphoenixcorneae.dialog.MaterialDialog
import com.fphoenixcorneae.dialog.NormalDialog
import com.fphoenixcorneae.dialog.NormalListDialog
import com.fphoenixcorneae.dialog.entity.DialogMenuItem
import kotlinx.android.synthetic.main.ac_dialog_home.*
import java.util.*

class DialogHomeActivity : AppCompatActivity(), OnChildClickListener {
    private val mContext: Context = this
    private val mMenuItems =
        ArrayList<DialogMenuItem>()
    private val mStringItems =
        arrayOf("收藏", "下载", "分享", "删除", "歌手", "专辑")
    private var mBasIn: BaseAnimatorSet? = null
    private var mBasOut: BaseAnimatorSet? = null
    fun setBasIn(bas_in: BaseAnimatorSet?) {
        mBasIn = bas_in
    }

    fun setBasOut(bas_out: BaseAnimatorSet?) {
        mBasOut = bas_out
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_dialog_home)
        mMenuItems.add(DialogMenuItem("收藏", R.mipmap.ic_winstyle_favor))
        mMenuItems.add(DialogMenuItem("下载", R.mipmap.ic_winstyle_download))
        mMenuItems.add(DialogMenuItem("分享", R.mipmap.ic_winstyle_share))
        mMenuItems.add(DialogMenuItem("删除", R.mipmap.ic_winstyle_delete))
        mMenuItems.add(DialogMenuItem("歌手", R.mipmap.ic_winstyle_artist))
        mMenuItems.add(DialogMenuItem("专辑", R.mipmap.ic_winstyle_album))
        mBasIn = BounceTopEnter()
        mBasOut = SlideBottomExit()
        val decorView = window.decorView
        val adapter = HomeAdapter(mContext)
        mElv.setAdapter(adapter)
        // extend all group
        for (i in mGroups.indices) {
            mElv.expandGroup(i)
        }
        mElv.setOnChildClickListener(this)
        mElv.setOnGroupClickListener { parent, v, groupPosition, id -> true }
    }

    override fun onChildClick(
        parent: ExpandableListView,
        v: View,
        groupPosition: Int,
        childPosition: Int,
        id: Long
    ): Boolean {
        /**Default Inner Dialog */
        if (groupPosition == 0) {
            when (childPosition) {
                0 -> {
                    NormalDialogStyleOne()
                }
                1 -> {
                    NormalDialogStyleTwo()
                }
                2 -> {
                    NormalDialogCustomAttr()
                }
                3 -> {
                    NormalDialogOneBtn()
                }
                4 -> {
                    NormalDialoThreeBtn()
                }
                5 -> {
                    MaterialDialogDefault()
                }
                6 -> {
                    MaterialDialogOneBtn()
                }
                7 -> {
                    MaterialDialogThreeBtns()
                }
                8 -> {
                    NormalListDialog()
                }
                9 -> {
                    NormalListDialogCustomAttr()
                }
                10 -> {
                    NormalListDialogNoTitle()
                }
                11 -> {
                    NormalListDialogStringArr()
                }
                12 -> {
                    NormalListDialogAdapter()
                }
                13 -> {
                    ActionSheetDialog()
                }
                14 -> {
                    ActionSheetDialogNoTitle()
                }
            }
        }
        /**Custom Dialog */
        if (groupPosition == 1) {
            when (childPosition) {
                0 -> {
                    val dialog = CustomBaseDialog(mContext)
                    dialog.show()
                    dialog.setCanceledOnTouchOutside(false)
                }
                1 -> {
                    val dialog = ShareBottomDialog(mContext, mElv)
                    dialog.showAnim(mBasIn) //
                        ?.show() //
                    // .show(0, 100);
                }
                2 -> {
                    val dialog = ShareTopDialog(mContext)
                    dialog.showAnim(mBasIn) //
                        ?.show()
                    // .show(0, 100);
                }
            }
        }
        /**Default Inner Anim */
        if (groupPosition == 2) {
            when (childPosition) {
                0 -> {
                    DiaogAnimChoose.showAnim(mContext)
                }
                1 -> {
                    DiaogAnimChoose.dissmissAnim(mContext)
                }
            }
        }
        /**Custom Anim */
        if (groupPosition == 3) {
            if (childPosition == 0) {
                val dialog =
                    IOSTaoBaoDialog(mContext, mElv.parent as View)
                dialog.show()
                // .show(0, 100);
            }
        }
        return false
    }

    private fun NormalDialogStyleOne() {
        val dialog = NormalDialog(mContext)
        dialog.content("是否确定退出程序?") //
            .showAnim(mBasIn) //
            .dismissAnim(mBasOut) //
            .blurEnabled(true)
            .animatedView(mElv)
            .windowsEnterAnim(WindowsSlipBottomEnter())
            .windowsExitAnim(null)
            .show()
        dialog.setOnBtnClickL(
            {
                T.showShort(mContext, "left")
                dialog.dismiss()
            },
            {
                T.showShort(mContext, "right")
                dialog.dismiss()
            })
    }

    private fun NormalDialogStyleTwo() {
        val dialog = NormalDialog(mContext)
        dialog.content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。") //
            .style(NormalDialog.STYLE_TWO) //
            .titleTextSize(23f) //
            .showAnim(mBasIn) //
            .dismissAnim(mBasOut) //
            .animatedView(mElv)
            .show()
        dialog.setOnBtnClickL(
            {
                T.showShort(mContext, "left")
                dialog.dismiss()
            },
            {
                T.showShort(mContext, "right")
                dialog.dismiss()
            })
    }

    private fun NormalDialogCustomAttr() {
        val dialog = NormalDialog(mContext)
        dialog.isTitleShow(false) //
            .bgColor(Color.parseColor("#000000")) //
            .cornerRadius(5f) //
            .content("是否确定退出程序?") //
            .contentGravity(Gravity.CENTER) //
            .contentTextColor(Color.parseColor("#ffffff")) //
            .dividerColor(Color.parseColor("#222222")) //
            .btnTextSize(15.5f, 15.5f) //
            .btnTextColor(
                Color.parseColor("#ffffff"),
                Color.parseColor("#ffffff")
            ) //
            .btnPressColor(Color.parseColor("#2B2B2B")) //
            .widthScale(0.85f) //
            .showAnim(mBasIn) //
            .dismissAnim(mBasOut) //
            .animatedView(mElv)
            .windowsEnterAnim(WindowsTaoBaoEnter())
            .windowsExitAnim(WindowsTaoBaoExit())
            .show()
        dialog.setOnBtnClickL(
            {
                T.showShort(mContext, "left")
                dialog.dismiss()
            },
            {
                T.showShort(mContext, "right")
                dialog.dismiss()
            })
    }

    private fun NormalDialogOneBtn() {
        val dialog = NormalDialog(mContext)
        dialog.content("你今天的抢购名额已用完~") //
            .btnNum(1)
            .btnText("继续逛逛") //
            .showAnim(mBasIn) //
            .dismissAnim(mBasOut) //
            .animatedView(mElv)
            .windowsEnterAnim(WindowsSlipTopEnter())
            .windowsExitAnim(null)
            .show()
        dialog.setOnBtnClickL({
            T.showShort(mContext, "middle")
            dialog.dismiss()
        })
    }

    private fun NormalDialoThreeBtn() {
        val dialog = NormalDialog(mContext)
        dialog.content("你今天的抢购名额已用完~") //
            .style(NormalDialog.STYLE_TWO) //
            .btnNum(3)
            .btnText("取消", "确定", "继续逛逛") //
            .showAnim(mBasIn) //
            .dismissAnim(mBasOut) //
            .animatedView(mElv)
            .windowsEnterAnim(WindowsOpenEnter())
            .windowsExitAnim(WindowsCloseExit())
            .show()
        dialog.setOnBtnClickL(
            {
                T.showShort(mContext, "left")
                dialog.dismiss()
            },
            {
                T.showShort(mContext, "right")
                dialog.dismiss()
            },
            {
                T.showShort(mContext, "middle")
                dialog.dismiss()
            })
    }

    private fun MaterialDialogDefault() {
        val dialog = MaterialDialog(mContext)
        dialog.content(
            "嗨！这是一个 MaterialDialogDefault. 它非常方便使用，你只需将它实例化，这个美观的对话框便会自动地显示出来。"
                    + "它简洁小巧，完全遵照 Google 2014 年发布的 Material Design 风格，希望你能喜欢它！^ ^"
        ) //
            .btnText("取消", "确定") //
            .showAnim(mBasIn) //
            .dismissAnim(mBasOut) //
            .show()
        dialog.setOnBtnClickL(

            //left btn click listener
            {
                T.showShort(mContext, "left")
                dialog.dismiss()
            },

            //right btn click listener
            {
                T.showShort(mContext, "right")
                dialog.dismiss()
            }
        )
    }

    private fun MaterialDialogThreeBtns() {
        val dialog = MaterialDialog(mContext)
        dialog.isTitleShow(false) //
            .btnNum(3)
            .content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。") //
            .btnText("确定", "取消", "知道了") //
            .showAnim(mBasIn) //
            .dismissAnim(mBasOut) //
            .show()
        dialog.setOnBtnClickL(

            //left btn click listener
            {
                T.showShort(mContext, "left")
                dialog.dismiss()
            },

            //right btn click listener
            {
                T.showShort(mContext, "right")
                dialog.dismiss()
            }
            ,

            //middle btn click listener
            {
                T.showShort(mContext, "middle")
                dialog.dismiss()
            }
        )
    }

    private fun MaterialDialogOneBtn() {
        val dialog = MaterialDialog(mContext)
        dialog //
            .btnNum(1)
            .content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。") //
            .btnText("确定") //
            .showAnim(mBasIn) //
            .dismissAnim(mBasOut) //
            .show()
        dialog.setOnBtnClickL({
            T.showShort(mContext, "middle")
            dialog.dismiss()
        })
    }

    private fun NormalListDialog() {
        val dialog =
            NormalListDialog(mContext, mMenuItems)
        dialog.title("请选择") //
            .showAnim(mBasIn) //
            .dismissAnim(mBasOut) //
            .show()
        dialog.setOnOperationItemClickL { parent, view, position, id ->
            T.showShort(
                mContext,
                mMenuItems[position].mOperationName
            )
            dialog.dismiss()
        }
    }

    private fun NormalListDialogCustomAttr() {
        val dialog =
            NormalListDialog(mContext, mMenuItems)
        dialog.title("请选择") //
            .titleTextSize_SP(18f) //
            .titleBgColor(Color.parseColor("#409ED7")) //
            .itemPressColor(Color.parseColor("#85D3EF")) //
            .itemTextColor(Color.parseColor("#303030")) //
            .itemTextSize(14f) //
            .cornerRadius(0f) //
            .widthScale(0.8f) //
            .show(R.style.myDialogAnim)
        dialog.setOnOperationItemClickL { parent, view, position, id ->
            T.showShort(
                mContext,
                mMenuItems[position].mOperationName
            )
            dialog.dismiss()
        }
    }

    private fun NormalListDialogNoTitle() {
        val dialog =
            NormalListDialog(mContext, mMenuItems)
        dialog.title("请选择") //
            .isTitleShow(false) //
            .itemPressColor(Color.parseColor("#85D3EF")) //
            .itemTextColor(Color.parseColor("#303030")) //
            .itemTextSize(15f) //
            .cornerRadius(2f) //
            .widthScale(0.75f) //
            .show()
        dialog.setOnOperationItemClickL { parent, view, position, id ->
            T.showShort(
                mContext,
                mMenuItems[position].mOperationName
            )
            dialog.dismiss()
        }
    }

    private fun NormalListDialogStringArr() {
        val dialog =
            NormalListDialog(mContext, mStringItems)
        dialog.title("请选择") //
            .layoutAnimation(null)
            .show(R.style.myDialogAnim)
        dialog.setOnOperationItemClickL { parent, view, position, id ->
            T.showShort(
                mContext,
                mMenuItems[position].mOperationName
            )
            dialog.dismiss()
        }
    }

    private fun NormalListDialogAdapter() {
        val dialog = NormalListDialog(
            mContext,
            TestAdapter(mContext, mMenuItems)
        )
        dialog.title("请选择") //
            .show(R.style.myDialogAnim)
        dialog.setOnOperationItemClickL { parent, view, position, id ->
            T.showShort(
                mContext,
                mMenuItems[position].mOperationName
            )
            dialog.dismiss()
        }
    }

    private fun ActionSheetDialog() {
        val stringItems =
            arrayOf<String?>("接收消息并提醒", "接收消息但不提醒", "收进群助手且不提醒", "屏蔽群消息")
        val dialog = ActionSheetDialog(
            mContext,
            stringItems,
            null
        )
        dialog.title("选择群消息提醒方式\r\n(该群在电脑的设置:接收消息并提醒)") //
            .titleTextSize_SP(14.5f) //
            .blurEnabled(true)
            .show()
        dialog.setOnOperationItemClickL { parent, view, position, id ->
            T.showShort(mContext, stringItems[position])
            dialog.dismiss()
        }
    }

    private fun ActionSheetDialogNoTitle() {
        val stringItems =
            arrayOf<String?>("版本更新", "帮助与反馈", "退出QQ")
        val dialog = ActionSheetDialog(
            mContext,
            stringItems,
            mElv
        )
        dialog.isTitleShow(false)
            .blurEnabled(true)
            .show()
        dialog.setOnOperationItemClickL { parent, view, position, id ->
            T.showShort(mContext, stringItems[position])
            dialog.dismiss()
        }
    }

    override fun onBackPressed() {
        val dialog = NormalDialog(mContext)
        dialog.content("亲,真的要走吗?再看会儿吧~(●—●)") //
            .style(NormalDialog.STYLE_TWO) //
            .titleTextSize(23f) //
            .btnText("继续逛逛", "残忍退出") //
            .btnTextColor(
                Color.parseColor("#383838"),
                Color.parseColor("#D4D4D4")
            ) //
            .btnTextSize(16f, 16f) //
            .showAnim(mBasIn) //
            .dismissAnim(mBasOut) //
            .show()
        dialog.setOnBtnClickL(
            {
                dialog.dismiss()
            },
            {
                dialog.superDismiss()
                finish()
            })
    }

    companion object {
        @JvmField
        var mGroups = arrayOf(
            "Default Inner Dialog",
            "Custom Dialog",
            "Default Inner Anim",
            "Custom Anim"
        )

        @JvmField
        var mChilds =
            arrayOf(
                arrayOf(
                    "NormalDialog Default(two btns)",  //0
                    "NormalDialog StyleTwo",  //1
                    "NormalDialog Custom Attr",  //2
                    "NormalDialog(one btn)",  //3
                    "NormalDialog(three btns)",  //4
                    "MaterialDialogDefault Default(two btns)",  //5
                    "MaterialDialogDefault(one btn)",  //6
                    "MaterialDialogDefault(three btns)",  //7
                    "NormalListDialog",  //8
                    "NormalListDialog Custom Attr",  //10
                    "NormalListDialog No Title",  //11
                    "NormalListDialog DataSource String[]",  //12
                    "NormalListDialog DataSource Adapter",  //13
                    "ActionSheetDialog",  //14
                    "ActionSheetDialog NoTitle" //15
                ), arrayOf(
                    "Custome Dialog extends BaseDialog",
                    "Custome Dialog extends BottomBaseDialog",
                    "Custome Dialog extends TopBaseDialog"
                ), arrayOf(
                    "Show Anim", "Dismiss Anim"
                ), arrayOf(
                    "Custom Anim like ios taobao"
                )
            )
    }
}