package com.tani.app.helper

import android.os.Handler
import androidx.viewpager.widget.ViewPager
import java.util.*


object TimerHelper {

    private val TAG = TimerTask::class.java.canonicalName

    private var timer: Timer? = null
    var task: TimerTask? = null

    fun autoSlide(viewPager: ViewPager, size: Int, time: Long) {
        var nextSlider = 0
        val handler = Handler()
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                handler.postDelayed({
                    nextSlider++
                    if (nextSlider == size) {
                        nextSlider = 0
                        viewPager.currentItem = nextSlider
                    } else {
                        viewPager.currentItem = nextSlider
                    }
                }, time)
            }
        }
        timer.schedule(task, 0, time)
        this.task = task
        this.timer = timer
    }

    fun looper(loops: Int, time: Long, listener: (Int) -> Unit) {
        var position = 0
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                if (loops > position) {
                    listener(position)
                    position++
                } else {
                    timer.cancel()
                }
            }
        }
        timer.schedule(task, 0, time)
    }

    fun start(time: Long) {
        timer?.cancel()
        timer?.schedule(task, 0, time)
    }

    fun cancel() {
        timer?.cancel()
    }
}