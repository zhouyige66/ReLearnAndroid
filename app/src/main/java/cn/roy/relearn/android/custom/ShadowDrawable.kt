package cn.roy.relearn.android.custom

import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import org.xmlpull.v1.XmlPullParser

/**
 * @Description: 自定义Drawable
 * @Author: zhouzongyi@cpic.com.cn
 * @Date: 11/19/20 8:40 AM
 * @Version: v1.0
 */
class ShadowDrawable : GradientDrawable() {
    var shadowWidth = 10f

    private var mPaint: Paint
    private var needDrawShadow = false
    private lateinit var originRect: Rect

    private lateinit var linearGradientLeft: LinearGradient
    private lateinit var linearGradientTop: LinearGradient
    private lateinit var linearGradientRight: LinearGradient
    private lateinit var linearGradientBottom: LinearGradient
    private lateinit var rectLeft: RectF
    private lateinit var rectTop: RectF
    private lateinit var rectRight: RectF
    private lateinit var rectBottom: RectF

    private lateinit var arcRectTopLeft: RectF
    private lateinit var arcRectTopRight: RectF
    private lateinit var arcRectBottomLeft: RectF
    private lateinit var arcRectBottomRight: RectF
    private lateinit var radialGradientTopLeft: RadialGradient
    private lateinit var radialGradientTopRight: RadialGradient
    private lateinit var radialGradientBottomLeft: RadialGradient
    private lateinit var radialGradientBottomRight: RadialGradient

    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL
    }

    override fun inflate(r: Resources, parser: XmlPullParser, attrs: AttributeSet, theme: Resources.Theme?) {
        super.inflate(r, parser, attrs, theme)
    }

    override fun onBoundsChange(r: Rect?) {
        Log.d("roy","执行了onBoundsChange："+r?.width()+"/"+r?.height())
        if (r == null) {
            needDrawShadow = false
            super.onBoundsChange(r)
            return
        }
        Log.d("roy", "原rect:$r")
        if ((r.left + shadowWidth) > (r.right - shadowWidth)
                || (r.top + shadowWidth) > (r.bottom - shadowWidth)) {
            Log.d("roy","宽度过小")
            needDrawShadow = false
            super.onBoundsChange(r)
            return
        }

        needDrawShadow = true
        originRect = r
        Log.d("roy","需要渲染")
        var newR = Rect((r.left+shadowWidth).toInt(), (r.top+shadowWidth).toInt(),
                (r.right-shadowWidth).toInt(), (r.bottom-shadowWidth).toInt())
        configShade()
        Log.d("roy", "新rect:$newR")
        super.onBoundsChange(newR)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if(needDrawShadow){
            mPaint.shader = linearGradientLeft
            canvas?.drawRect(rectLeft, mPaint)
            mPaint.shader = linearGradientTop
            canvas?.drawRect(rectTop, mPaint)
            mPaint.shader = linearGradientRight
            canvas?.drawRect(rectRight, mPaint)
            mPaint.shader = linearGradientBottom
            canvas?.drawRect(rectBottom, mPaint)

            // 画扇形
            mPaint.shader = radialGradientTopLeft
            canvas?.drawArc(arcRectTopLeft, 180f, 90f, true, mPaint)
            mPaint.shader = radialGradientTopRight
            canvas?.drawArc(arcRectTopRight, 270f, 90f, true, mPaint)
            mPaint.shader = radialGradientBottomRight
            canvas?.drawArc(arcRectBottomRight, 0f, 90f, true, mPaint)
            mPaint.shader = radialGradientBottomLeft
            canvas?.drawArc(arcRectBottomLeft, 90f, 90f, true, mPaint)
        }
    }

    private fun configShade() {
        Log.d("roy", String.format("%d/%d", originRect.left, originRect.right))

        var mWidth = originRect.width()
        var mHeight = originRect.height()
        rectLeft = RectF(0f, shadowWidth, shadowWidth, mHeight - shadowWidth)
        rectTop = RectF(shadowWidth, 0f, mWidth - shadowWidth, shadowWidth)
        rectRight = RectF(mWidth - shadowWidth, shadowWidth, mWidth.toFloat(), mHeight - shadowWidth)
        rectBottom = RectF(shadowWidth, mHeight - shadowWidth, mWidth - shadowWidth, mHeight.toFloat())

        arcRectTopLeft = RectF(0f, 0f, shadowWidth * 2, shadowWidth * 2)
        arcRectTopRight = RectF(mWidth - 2 * shadowWidth, 0f, mWidth.toFloat(), shadowWidth * 2)
        arcRectBottomRight = RectF(mWidth - 2 * shadowWidth, mHeight - 2 * shadowWidth, mWidth.toFloat(), mHeight.toFloat())
        arcRectBottomLeft = RectF(0f, mHeight - 2 * shadowWidth, shadowWidth * 2, mHeight.toFloat())

        linearGradientLeft = LinearGradient(rectLeft.right, rectLeft.top, rectLeft.left,
                rectLeft.top, Color.parseColor("#30FF0000"), Color.TRANSPARENT, Shader.TileMode.CLAMP)
        linearGradientTop = LinearGradient(rectTop.left, rectTop.bottom, rectTop.left,
                rectTop.top, Color.parseColor("#30FF0000"), Color.TRANSPARENT, Shader.TileMode.CLAMP)
        linearGradientRight = LinearGradient(rectRight.left, rectRight.top, rectRight.right,
                rectRight.top, Color.parseColor("#30FF0000"), Color.TRANSPARENT, Shader.TileMode.CLAMP)
        linearGradientBottom = LinearGradient(rectBottom.left, rectBottom.top, rectBottom.left,
                rectBottom.bottom, Color.parseColor("#30FF0000"), Color.TRANSPARENT, Shader.TileMode.CLAMP)

        radialGradientTopLeft = RadialGradient(arcRectTopLeft.centerX(), arcRectTopLeft.centerY(),
                shadowWidth, Color.parseColor("#30FF0000"), Color.TRANSPARENT, Shader.TileMode.CLAMP)
        radialGradientTopRight = RadialGradient(arcRectTopRight.centerX(), arcRectTopRight.centerY(),
                shadowWidth, Color.parseColor("#30FF0000"), Color.TRANSPARENT, Shader.TileMode.CLAMP)
        radialGradientBottomLeft = RadialGradient(arcRectBottomLeft.centerX(), arcRectBottomLeft.centerY(),
                shadowWidth, Color.parseColor("#30FF0000"), Color.TRANSPARENT, Shader.TileMode.CLAMP)
        radialGradientBottomRight = RadialGradient(arcRectBottomRight.centerX(), arcRectBottomRight.centerY(),
                shadowWidth, Color.parseColor("#30FF0000"), Color.TRANSPARENT, Shader.TileMode.CLAMP)
    }

}