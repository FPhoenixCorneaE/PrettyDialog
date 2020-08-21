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
import com.fphoenixcorneae.dialog.base.BaseDialog
import com.fphoenixcorneae.dialog.entity.DialogMenuItem
import com.fphoenixcorneae.utils.CornerUtils
import java.util.*

class NormalListDialog : BaseDialog<NormalListDialog> {
    /** ListView  */
    private var mLv: ListView? = null

    /** title  */
    private var mTvTitle: TextView? = null

    /** corner radius,dp(圆角程度,单位dp)  */
    private var mCornerRadius = 5f

    /** title background color(标题背景颜色)  */
    private var mTitleBgColor = Color.parseColor("#303030")

    /** title text(标题)  */
    private var mTitle = "提示"

    /** title textColor(标题颜色)  */
    private var mTitleTextColor = Color.parseColor("#ffffff")

    /** title textSize(标题字体大小,单位sp)  */
    private var mTitleTextSize = 16.5f

    /** ListView background color(ListView背景色)  */
    private var mLvBgColor = Color.parseColor("#ffffff")

    /** divider color(ListView divider颜色)  */
    private var mDividerColor = Color.LTGRAY

    /** divider height(ListView divider高度)  */
    private var mDividerHeight = 0.8f

    /** item press color(ListView item按住颜色)  */
    private var mItemPressColor = Color.parseColor("#ffcccccc")

    /** item textColor(ListView item文字颜色)  */
    private var mItemTextColor = Color.parseColor("#303030")

    /** item textSize(ListView item文字大小)  */
    private var mItemTextSize = 15f

    /** item extra padding(ListView item额外padding)  */
    private var mItemExtraLeft = 0
    private var mItemExtraTop = 0
    private var mItemExtraRight = 0
    private var mItemExtraBottom = 0

    /** enable title show(是否显示标题)  */
    private var mIsTitleShow = true

    /** adapter(自定义适配器)  */
    private var mAdapter: BaseAdapter? = null

    /** operation items(操作items)  */
    private var mContents = ArrayList<DialogMenuItem>()
    private var mOnOperationItemClickListener: ((dialog: NormalListDialog, parent: AdapterView<*>?, view: View?, position: Int, id: Long) -> Unit)? =
        null
    private var mLac: LayoutAnimationController? = null

    constructor(
        context: Context,
        baseItems: ArrayList<DialogMenuItem>
    ) : super(context) {
        mContents.addAll(baseItems)
        init()
    }

    constructor(context: Context, items: Array<String>) : super(
        context
    ) {
        mContents = ArrayList()
        for (item in items) {
            val customBaseItem = DialogMenuItem(item, 0)
            mContents.add(customBaseItem)
        }
        init()
    }

    constructor(context: Context, adapter: BaseAdapter?) : super(context) {
        mAdapter = adapter
        init()
    }

    private fun init() {
        widthScale(0.8f)
        /** LayoutAnimation  */
        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 2f, Animation.RELATIVE_TO_SELF,
            0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
        )
        animation.interpolator = DecelerateInterpolator()
        animation.duration = 550
        mLac = LayoutAnimationController(animation, 0.12f)
        mLac!!.interpolator = DecelerateInterpolator()
    }

    override fun onCreateView(): View? {
        val llContainer = LinearLayout(mContext)
        llContainer.orientation = LinearLayout.VERTICAL
        /** title  */
        mTvTitle = TextView(mContext)
        mTvTitle!!.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mTvTitle!!.isSingleLine = true
        mTvTitle!!.setPadding(dp2px(18f), dp2px(10f), 0, dp2px(10f))
        llContainer.addView(mTvTitle)
        /** listView  */
        mLv = ListView(mContext)
        mLv!!.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mLv!!.cacheColorHint = Color.TRANSPARENT
        mLv!!.setFadingEdgeLength(0)
        mLv!!.isVerticalScrollBarEnabled = false
        mLv!!.selector = ColorDrawable(Color.TRANSPARENT)
        llContainer.addView(mLv)
        return llContainer
    }

    override fun setUiBeforeShow() {
        /** title  */
        val radius = dp2px(mCornerRadius).toFloat()
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
            mOnOperationItemClickListener?.invoke(this, parent, view, position, id)
        }
        mLv!!.layoutAnimation = mLac
    }

    /** set title background color(设置标题栏背景色) @return NormalListDialog  */
    fun titleBgColor(titleBgColor: Int): NormalListDialog {
        mTitleBgColor = titleBgColor
        return this
    }

    /** set title text(设置标题内容)  */
    fun title(title: String): NormalListDialog {
        mTitle = title
        return this
    }

    /** set title textSize(设置标题字体大小)  */
    fun titleTextSize_SP(titleTextSize_SP: Float): NormalListDialog {
        mTitleTextSize = titleTextSize_SP
        return this
    }

    /** set title textColor(设置标题字体颜色)  */
    fun titleTextColor(titleTextColor: Int): NormalListDialog {
        mTitleTextColor = titleTextColor
        return this
    }

    /*** enable title show(设置标题是否显示)  */
    fun isTitleShow(isTitleShow: Boolean): NormalListDialog {
        mIsTitleShow = isTitleShow
        return this
    }

    /** set ListView background color(设置ListView背景)  */
    fun lvBgColor(lvBgColor: Int): NormalListDialog {
        mLvBgColor = lvBgColor
        return this
    }

    /** set corner radius(设置圆角程度,单位dp)  */
    fun cornerRadius(cornerRadius_DP: Float): NormalListDialog {
        mCornerRadius = cornerRadius_DP
        return this
    }

    /** set divider color(ListView divider颜色)  */
    fun dividerColor(dividerColor: Int): NormalListDialog {
        mDividerColor = dividerColor
        return this
    }

    /** set divider height(ListView divider高度)  */
    fun dividerHeight(dividerHeight_DP: Float): NormalListDialog {
        mDividerHeight = dividerHeight_DP
        return this
    }

    /** set item press color(item按住颜色)  */
    fun itemPressColor(itemPressColor: Int): NormalListDialog {
        mItemPressColor = itemPressColor
        return this
    }

    /** set item textColor(item字体颜色)  */
    fun itemTextColor(itemTextColor: Int): NormalListDialog {
        mItemTextColor = itemTextColor
        return this
    }

    /** set item textSize(item字体大小)  */
    fun itemTextSize(itemTextSize_SP: Float): NormalListDialog {
        mItemTextSize = itemTextSize_SP
        return this
    }

    /** set item height(item高度)  */
    fun setItemExtraPadding(
        itemLeft: Int,
        itemTop: Int,
        itemRight: Int,
        itemBottom: Int
    ): NormalListDialog {
        mItemExtraLeft = dp2px(itemLeft.toFloat())
        mItemExtraTop = dp2px(itemTop.toFloat())
        mItemExtraRight = dp2px(itemRight.toFloat())
        mItemExtraBottom = dp2px(itemBottom.toFloat())
        return this
    }

    /** set layoutAnimation(设置layout动画 ,传入null将不显示layout动画)  */
    fun layoutAnimation(lac: LayoutAnimationController?): NormalListDialog {
        mLac = lac
        return this
    }

    fun setOnOperationItemClickListener(
        onOperationItemClickListener: ((
            dialog: NormalListDialog,
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) -> Unit)?
    ): NormalListDialog {
        mOnOperationItemClickListener = onOperationItemClickListener
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
            tvItem.setTextColor(mItemTextColor)
            tvItem.setTextSize(TypedValue.COMPLEX_UNIT_SP, mItemTextSize)
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
            val left = if (item.mResId == 0) dp2px(18f) else dp2px(16f)
            val top = dp2px(10f)
            val right = 0
            val bottom = dp2px(10f)
            llItem.setPadding(
                left + mItemExtraLeft,
                top + mItemExtraTop,
                right + mItemExtraRight,
                bottom + mItemExtraBottom
            )
            ivItem.setImageResource(item.mResId)
            tvItem.text = item.mOperationName
            ivItem.visibility = if (item.mResId == 0) View.GONE else View.VISIBLE
            return llItem
        }
    }
}