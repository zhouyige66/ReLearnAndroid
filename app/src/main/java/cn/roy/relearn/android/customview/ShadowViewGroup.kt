package cn.roy.relearn.android.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @Description: 阴影视图
 * @Author: zhouzongyi@cpic.com.cn
 * @Date: 11/18/20 6:36 PM
 * @Version: v1.0
 */
open class ShadowViewGroup @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var mWidth: Int = 0
    var mHeight: Int = 0
    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var shadowWidth = 20f
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
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mWidth = w
        mHeight = h
        configShade()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
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

    private fun configShade() {
        Log.d("roy", String.format("%d/%d", mWidth, mHeight))
        rectLeft = RectF(0f, shadowWidth, shadowWidth, mHeight - shadowWidth)
        rectTop = RectF(shadowWidth, 0f, mWidth - shadowWidth, shadowWidth)
        rectRight = RectF(mWidth - shadowWidth, shadowWidth, mWidth.toFloat(), mHeight - shadowWidth)
        rectBottom = RectF(shadowWidth, mHeight - shadowWidth, mWidth - shadowWidth, mHeight.toFloat())

        arcRectTopLeft = RectF(0f, 0f, shadowWidth * 2, shadowWidth * 2)
        arcRectTopRight = RectF(width - 2 * shadowWidth, 0f, mWidth.toFloat(), shadowWidth * 2)
        arcRectBottomLeft = RectF(0f, mHeight - 2 * shadowWidth, shadowWidth * 2, mHeight.toFloat())
        arcRectBottomRight = RectF(mWidth - 2 * shadowWidth, mHeight - 2 * shadowWidth, mWidth.toFloat(), mHeight.toFloat())

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