package com.example.banana.customView

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class Card @JvmOverloads constructor(context: Context,
                                     attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    // 뷰의 내용이 렌더링 될때 호출 됩니다
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 여기다가 원하는걸 그리면 됩니다 :)
    }
}