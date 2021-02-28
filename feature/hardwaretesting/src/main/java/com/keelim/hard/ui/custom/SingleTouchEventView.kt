/*
 * Designed and developed by 2020 keelim (Jaehyun Kim)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keelim.hard.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View

class SingleTouchEventView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
  private val paint = Paint()
  private val path = Path()
  private var mVelocityTracker: VelocityTracker? = null
  override fun onDraw(canvas: Canvas) {
    canvas.drawPath(path, paint)
  }

  init {
    paint.isAntiAlias = true
    paint.strokeWidth = 7f
    paint.color = Color.BLUE
    paint.style = Paint.Style.STROKE
    paint.strokeJoin = Paint.Join.ROUND
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    val eventX = event.x
    val eventY = event.y
    when (event.action) {
      MotionEvent.ACTION_DOWN -> {
        path.moveTo(eventX, eventY)
        mVelocityTracker = VelocityTracker.obtain()
        mVelocityTracker!!.addMovement(event)
        return true
      }
      MotionEvent.ACTION_MOVE -> path.lineTo(eventX, eventY)
      MotionEvent.ACTION_UP -> {
      }
      else -> return false
    }

    // Schedules a repaint.
    invalidate()
    return true
  }
}
