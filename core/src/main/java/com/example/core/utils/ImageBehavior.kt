package com.example.core.utils

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.core.R
import com.google.android.material.appbar.MaterialToolbar
import de.hdodenhof.circleimageview.CircleImageView

class ImageBehavior() : CoordinatorLayout.Behavior<CircleImageView>() {

    constructor(ctx: Context, attrs: AttributeSet) : this()

    private val MIN_AVATAR_PERCENTAGE_SIZE = 0.3f
    private val EXTRA_FINAL_AVATAR_PADDING = 80

    private val TAG = "behavior"
    private var mContext: Context? = null

    private var mCustomFinalYPosition = 0f
    private var mCustomStartXPosition = 0f
    private var mCustomStartToolbarPosition = 0f
    private var mCustomStartHeight = 0f
    private var mCustomFinalHeight = 0f

    private var mAvatarMaxSize = 0f
    private var mFinalLeftAvatarPadding = 0f
    private val mStartPosition = 0f
    private var mStartXPosition = 0
    private var mStartToolbarPosition = 0f
    private var mStartYPosition = 0
    private var mFinalYPosition = 0
    private var mStartHeight = 0
    private var mFinalXPosition = 0
    private var mChangeBehaviorPoint = 0f

    fun AvatarImageBehavior(context: Context, attrs: AttributeSet?) {
        mContext = context
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ImageBehavior)
            mCustomFinalYPosition =
                a.getDimension(R.styleable.ImageBehavior_finalYPosition, 0f)
            mCustomStartXPosition =
                a.getDimension(R.styleable.ImageBehavior_startXPosition, 0f)
            mCustomStartToolbarPosition =
                a.getDimension(R.styleable.ImageBehavior_startToolbarPosition, 0f)
            mCustomStartHeight = a.getDimension(R.styleable.ImageBehavior_startHeight, 0f)
            mCustomFinalHeight = a.getDimension(R.styleable.ImageBehavior_finalHeight, 0f)
            a.recycle()
        }
        init()
        mFinalLeftAvatarPadding = context.resources.getDimension(
            R.dimen.spacing_normal
        )
    }

    private fun init() {
        bindDimensions()
    }

    private fun bindDimensions() {
        mAvatarMaxSize = mContext!!.resources.getDimension(R.dimen.image_width)
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: CircleImageView,
        dependency: View
    ): Boolean {
        return dependency is MaterialToolbar
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: CircleImageView,
        dependency: View
    ): Boolean {
        maybeInitProperties(child, dependency)
        val maxScrollDistance = mStartToolbarPosition.toInt()
        val expandedPercentageFactor = dependency.y / maxScrollDistance
        if (expandedPercentageFactor < mChangeBehaviorPoint) {
            val heightFactor =
                (mChangeBehaviorPoint - expandedPercentageFactor) / mChangeBehaviorPoint
            val distanceXToSubtract = ((mStartXPosition - mFinalXPosition)
                    * heightFactor) + child.height / 2
            val distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
                    * (1f - expandedPercentageFactor)) + child.height / 2
            child.x = mStartXPosition - distanceXToSubtract
            child.y = mStartYPosition - distanceYToSubtract
            val heightToSubtract = (mStartHeight - mCustomFinalHeight) * heightFactor
            val lp = child.layoutParams as CoordinatorLayout.LayoutParams
            lp.width = (mStartHeight - heightToSubtract).toInt()
            lp.height = (mStartHeight - heightToSubtract).toInt()
            child.layoutParams = lp
            dependency as MaterialToolbar
            if (lp.height == 0) dependency.setTitleTextColor(Color.WHITE)
            else dependency.setTitleTextColor(Color.parseColor("#161A20"))
        } else {
            val distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
                    * (1f - expandedPercentageFactor)) + mStartHeight / 2
            child.x = (mStartXPosition - child.width / 2).toFloat()
            child.y = mStartYPosition - distanceYToSubtract
            val lp = child.layoutParams as CoordinatorLayout.LayoutParams
            lp.width = mStartHeight
            lp.height = mStartHeight
            child.layoutParams = lp
        }
        return true
    }

    private fun maybeInitProperties(child: CircleImageView, dependency: View) {
        if (mStartYPosition == 0) mStartYPosition = dependency.y.toInt()
        if (mFinalYPosition == 0) mFinalYPosition = dependency.height / 2
        if (mStartHeight == 0) mStartHeight = child.height
        if (mStartXPosition == 0) mStartXPosition = (child.x + child.width / 2).toInt()
        mContext?.resources?.getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material)
            ?.let {
                if (mFinalXPosition == 0) mFinalXPosition =
                    it + mCustomFinalHeight.toInt() / 2
            }
        if (mStartToolbarPosition == 0f) mStartToolbarPosition = dependency.y
        if (mChangeBehaviorPoint == 0f) {
            mChangeBehaviorPoint =
                (child.height - mCustomFinalHeight) / (2f * (mStartYPosition - mFinalYPosition))
        }
    }
}