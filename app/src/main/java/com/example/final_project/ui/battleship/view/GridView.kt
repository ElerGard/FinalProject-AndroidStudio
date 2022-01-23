package com.example.final_project.ui.battleship.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GridView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
  companion object
    {
        // Я осознал как работает MVVM тема оч поздно, поэтому так
        var Red = 63;
        var Green = 137;
        var Blue = 188;
        var alph = 100;
    }

    private var size = 10

    private var selectedRow = -1
    private var selectedCol = -1

    val table: Array<Array<Int>> = Array(size) { Array(size, { 0 }) }

    private var cellSizePixels = 0F

    private val thickLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 4F
    }

    private val rect = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.argb(255, Red, Green, Blue)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val sizePixels = Math.min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(sizePixels, sizePixels)
    }

    override fun onDraw(canvas: Canvas)
    {
        cellSizePixels = (width / size).toFloat()
        fillCells(canvas)
        drawLines(canvas)
    }

    private fun fillCells(canvas: Canvas)
    {
        if (selectedCol == -1 && selectedRow == -1) {return}
        for (r in 0 until size)
            for (c in 0 until size)
            {
                if (r == selectedRow && c == selectedCol)
                {
                    table[r][c] = 1

                }
                if (table[r][c] == 1)
                {
                    fillCell(canvas, r, c, rect)
                }
            }
    }

    private  fun fillCell(canvas: Canvas, r: Int, c: Int, paint: Paint)
    {
        canvas.drawRect(c * cellSizePixels, r * cellSizePixels, (c + 1) * cellSizePixels, (r + 1) * cellSizePixels, rect)
    }

    private  fun drawLines(canvas: Canvas)
    {
        canvas.drawRect(0F,0F, width.toFloat(), height.toFloat(), thickLinePaint)

        for (i in 1 until size)
        {
            canvas.drawLine(
                    i * cellSizePixels,
                    0F,
                    i * cellSizePixels,
                    height.toFloat(),
                    thickLinePaint)

            canvas.drawLine(
                    0F,
                    i * cellSizePixels,
                    width.toFloat(),
                    i * cellSizePixels,
                    thickLinePaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action)
        {
            MotionEvent.ACTION_DOWN -> {
                handleTouchEvent(event.x, event.y)
                true
            }
            else -> false
        }
    }

    private fun handleTouchEvent(x: Float, y: Float) {
        selectedRow =  (y / cellSizePixels).toInt()
        selectedCol =  (x / cellSizePixels).toInt()
        rect.color = Color.argb(alph, Red, Green, Blue)
        invalidate()
    }

}