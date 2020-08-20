package com.fphoenixcorneae.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.LayoutAnimationController
import android.view.animation.TranslateAnimation
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import com.fphoenixcorneae.dialog.entity.DialogMenuItem
import com.fphoenixcorneae.utils.CornerUtils
import com.fphoenixcorneae.dialog.base.BottomBaseDialog
import java.util.*

/**
 * Dialog like iOS ActionSheet(iOS风格对话框)
 */
class ActionSheetDialog : BottomBaseDialog<ActionSheetDialog> {
    /** ListView  */
    private var mLv: ListView? = null

    /** title  */
    private var mTvTitle: TextView? = null

    /** title underline(标题下划线)  */
    private var mVLineTitle: View? = null

    /** mCancel button(取消按钮)  */
    private var mTvCancel: TextView? = null

    /** corner radius,dp(圆角程度,单位dp)  */
    private var mCornerRadius = 5f

    /** title background color(标题背景颜色)  */
    private var mTitleBgColor = Color.parseColor("#ddffffff")

    /** title text(标题)  */
    private var mTitle = "提示"

    /** title height(标题栏高度)  */
    private var mTitleHeight = 48f

    /** title textColor(标题颜色)  */
    private var mTitleTextColor = Color.parseColor("#8F8F8F")

    /** title textSize(标题字体大小,单位sp)  */
    private var mTitleTextSize = 17.5f

    /** ListView background color(ListView背景色)  */
    private var mLvBgColor = Color.parseColor("#ddffffff")

    /** divider color(ListView divider颜色)  */
    private var mDividerColor = Color.parseColor("#D7D7D9")

    /** divider height(ListView divider高度)  */
    private var mDividerHeight = 0.8f

    /** item press color(ListView item按住颜色)  */
    private var mItemPressColor = Color.parseColor("#ffcccccc")

    /** item textColor(ListView item文字颜色)  */
    private var mItemTextColor = Color.parseColor("#44A2FF")

    /** item textSize(ListView item文字大小)  */
    private var mItemTextSize = 17.5f

    /** item height(ListView item高度)  */
    private var mItemHeight = 48f

    /** enable title show(是否显示标题)  */
    private var mIsTitleShow = true

    /*** cancel btn text(取消按钮内容)  */
    private var mCancelText = "取消"

    /** cancel btn text color(取消按钮文字颜色)  */
    private var mCancelTextColor = Color.parseColor("#44A2FF")

    /** cancel btn text size(取消按钮文字大小)  */
    private var mCancelTextSize = 17.5f

    /** adapter(自定义适配器)  */
    private var mAdapter: BaseAdapter? = null

    /** operation items(操作items)  */
    private var mContents = ArrayList<DialogMenuItem>()

    private var mOnOperationItemClickListener: ((parent: AdapterView<*>?, view: View?, position: Int, id: Long) -> Unit)? =
        null

    private var mLac: LayoutAnimationController? = null

    fun setOnOperationItemClickL(
        onOperationItemClickListener: ((
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) -> Unit)?
    ) {
        mOnOperationItemClickListener = onOperationItemClickListener
    }

    constructor(
        context: Context,
        baseItems: ArrayList<DialogMenuItem>,
        animateView: View?
    ) : super(context, animateView) {
        mContents.addAll(baseItems)
        init()
    }

    constructor(
        context: Context,
        items: Array<String?>,
        animateView: View?
    ) : super(context, animateView) {
        mContents = ArrayList()
        for (item in items) {
            val customBaseItem = DialogMenuItem(item, 0)
            mContents.add(customBaseItem)
        }
        init()
    }

    constructor(
        context: Context,
        adapter: BaseAdapter?,
        animateView: View?
    ) : super(context, animateView) {
        mAdapter = adapter
        init()
    }

    private fun init() {
        widthScale(0.95f)
        /** LayoutAnimation  */
        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
            0f, Animation.RELATIVE_TO_SELF, 6f, Animation.RELATIVE_TO_SELF, 0f
        )
        animation.interpolator = DecelerateInterpolator()
        animation.duration = 350
        animation.startOffset = 150
        mLac = LayoutAnimationController(animation, 0.12f)
        mLac!!.interpolator = DecelerateInterpolator()
    }

    override fun onCreateView(): View? {
        val llContainer = LinearLayout(mContext)
        llContainer.orientation = LinearLayout.VERTICAL
        llContainer.setBackgroundColor(Color.TRANSPARENT)
        /** title  */
        mTvTitle = TextView(mContext)
        mTvTitle!!.gravity = Gravity.CENTER
        mTvTitle!!.setPadding(dp2px(10f), dp2px(5f), dp2px(10f), dp2px(5f))
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = dp2px(20f)
        llContainer.addView(mTvTitle, params)
        /** title underline  */
        mVLineTitle = View(mContext)
        llContainer.addView(mVLineTitle)
        /** listView  */
        mLv = ListView(mContext)
        mLv!!.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        )
        mLv!!.cacheColorHint = Color.TRANSPARENT
        mLv!!.setFadingEdgeLength(0)
        mLv!!.isVerticalScrollBarEnabled = false
        mLv!!.selector = ColorDrawable(Color.TRANSPARENT)
        llContainer.addView(mLv)
        /** mCancel btn  */
        mTvCancel = TextView(mContext)
        mTvCancel!!.gravity = Gravity.CENTER
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.topMargin = dp2px(7f)
        lp.bottomMargin = dp2px(7f)
        mTvCancel!!.layoutParams = lp
        llContainer.addView(mTvCancel)
        return llContainer
    }

    override fun setUiBeforeShow() {
        /** title  */
        val radius = dp2px(mCornerRadius).toFloat()
        mTvTitle!!.height = dp2px(mTitleHeight)
        mTvTitle!!.setBackgroundDrawable(
            CornerUtils.cornerDrawable(
                mTitleBgColor, floatArrayOf(
                    radius, radius, radius,
                    radius, 0f, 0f, 0f, 0f
                )
            )
        )
        mTvTitle!!.text = mTitle
        mTvTitle!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleTextSize)
        mTvTitle!!.setTextColor(mTitleTextColor)
        mTvTitle!!.visibility = if (mIsTitleShow) View.VISIBLE else View.GONE
        /** title underline  */
        mVLineTitle!!.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp2px(mDividerHeight)
        )
        mVLineTitle!!.setBackgroundColor(mDividerColor)
        mVLineTitle!!.visibility = if (mIsTitleShow) View.VISIBLE else View.GONE
        /** mCancel btn  */
        mTvCancel!!.height = dp2px(mItemHeight)
        mTvCancel!!.text = mCancelText
        mTvCancel!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, mCancelTextSize)
        mTvCancel!!.setTextColor(mCancelTextColor)
        mTvCancel!!.setBackgroundDrawable(
            CornerUtils.listItemSelector(
                radius,
                mLvBgColor,
                mItemPressColor,
                1,
                0
            )
        )
        mTvCancel!!.setOnClickListener { dismiss() }
        /** listView  */
        mLv!!.divider = ColorDrawable(mDividerColor)
        mLv!!.dividerHeight = dp2px(mDividerHeight)
        if (mIsTitleShow) {
            mLv!!.setBackgroundDrawable(
                CornerUtils.cornerDrawable(
                    mLvBgColor, floatArrayOf(
                        0f, 0f, 0f, 0f, radius, radius, radius,
                        radius
                    )
                )
            )
        } else {
            mLv!!.setBackgroundDrawable(CornerUtils.cornerDrawable(mLvBgColor, radius))
        }
        if (mAdapter == null) {
            mAdapter = ListDialogAdapter()
        }
        mLv!!.adapter = mAdapter
        mLv!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            mOnOperationItemClickListener?.invoke(parent, view, position, id)
        }
        mLv!!.layoutAnimation = mLac
    }

    /** set title background color(设置标题栏背景色)  */
    fun titleBgColor(titleBgColor: Int): ActionSheetDialog {
        mTitleBgColor = titleBgColor
        return this
    }

    /** set title text(设置标题内容)  */
    fun title(title: String): ActionSheetDialog {
        mTitle = title
        return this
    }

    /** set titleHeight(设置标题高度)  */
    fun titleHeight(titleHeight: Float): ActionSheetDialog {
        mTitleHeight = titleHeight
        return this
    }

    /** set title textSize(设置标题字体大小)  */
    fun titleTextSize_SP(titleTextSize_SP: Float): ActionSheetDialog {
        mTitleTextSize = titleTextSize_SP
        return this
    }

    /** set title textColor(设置标题字体颜色)  */
    fun titleTextColor(titleTextColor: Int): ActionSheetDialog {
        mTitleTextColor = titleTextColor
        return this
    }

    /** enable title show(设置标题是否显示)  */
    fun isTitleShow(isTitleShow: Boolean): ActionSheetDialog {
        mIsTitleShow = isTitleShow
        return this
    }

    /** set ListView background color(设置ListView背景)  */
    fun lvBgColor(lvBgColor: Int): ActionSheetDialog {
        mLvBgColor = lvBgColor
        return this
    }

    /** set corner radius(设置圆角程度,单位dp)  */
    fun cornerRadius(cornerRadius_DP: Float): ActionSheetDialog {
        mCornerRadius = cornerRadius_DP
        return this
    }

    /** set divider color(ListView divider颜色)  */
    fun dividerColor(dividerColor: Int): ActionSheetDialog {
        mDividerColor = dividerColor
        return this
    }

    /** set divider height(ListView divider高度)  */
    fun dividerHeight(dividerHeight_DP: Float): ActionSheetDialog {
        mDividerHeight = dividerHeight_DP
        return this
    }

    /** set item press color(item按住颜色)  */
    fun itemPressColor(itemPressColor: Int): ActionSheetDialog {
        mItemPressColor = itemPressColor
        return this
    }

    /** set item textColor(item字体颜色)* @return ActionSheetDialog  */
    fun itemTextColor(itemTextColor: Int): ActionSheetDialog {
        mItemTextColor = itemTextColor
        return this
    }

    /** set item textSize(item字体大小)  */
    fun itemTextSize(itemTextSize_SP: Float): ActionSheetDialog {
        mItemTextSize = itemTextSize_SP
        return this
    }

    /** set item height(item高度)  */
    fun itemHeight(itemHeight_DP: Float): ActionSheetDialog {
        mItemHeight = itemHeight_DP
        return this
    }

    /** set layoutAnimation(设置layout动画 ,传入null将不显示layout动画)  */
    fun layoutAnimation(lac: LayoutAnimationController?): ActionSheetDialog {
        mLac = lac
        return this
    }

    /** set cancel btn text(设置取消按钮内容)  */
    fun cancelText(cancelText: String): ActionSheetDialog {
        mCancelText = cancelText
        return this
    }

    /** cancel btn text color(取消按钮文字颜色)  */
    fun cancelText(cancelTextColor: Int): ActionSheetDialog {
        mCancelTextColor = cancelTextColor
        return this
    }

    /** cancel btn text size(取消按钮文字大小)  */
    fun cancelTextSize(cancelTextSize: Float): ActionSheetDialog {
        mCancelTextSize = cancelTextSize
        return this
    }

    internal inner class ListDialogAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return mContents.size
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            val item = mContents[position]
            val llItem = LinearLayout(mContext)
            llItem.orientation = LinearLayout.HORIZONTAL
            llItem.gravity = Gravity.CENTER_VERTICAL
            val ivItem = ImageView(mContext)
            ivItem.setPadding(0, 0, dp2px(15f), 0)
            llItem.addView(ivItem)
            val tvItem = TextView(mContext)
            tvItem.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            tvItem.isSingleLine = true
            tvItem.gravity = Gravity.CENTER
            tvItem.setTextColor(mItemTextColor)
            tvItem.setTextSize(TypedValue.COMPLEX_UNIT_SP, mItemTextSize)
            tvItem.height = dp2px(mItemHeight)
            llItem.addView(tvItem)
            val radius = dp2px(mCornerRadius).toFloat()
            if (mIsTitleShow) {
                llItem.setBackgroundDrawable(
                    CornerUtils.listItemSelector(
                        radius, Color.TRANSPARENT, mItemPressColor,
                        position == mContents.size - 1
                    )
                )
            } else {
                llItem.setBackgroundDrawable(
                    CornerUtils.listItemSelector(
                        radius, Color.TRANSPARENT, mItemPressColor,
                        mContents.size, position
                    )
                )
            }
            ivItem.setImageResource(item.mResId)
            tvItem.text = item.mOperationName
            ivItem.visibility = if (item.mResId == 0) View.GONE else View.VISIBLE
            return llItem
        }
    }
}