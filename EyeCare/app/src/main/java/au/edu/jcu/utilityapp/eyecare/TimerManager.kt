package au.edu.jcu.utilityapp.eyecare

import android.os.CountDownTimer

class TimerManager(time: Long) {
    private lateinit var timer: CountDownTimer
    val timerLength: Long = time


    fun timerTick() {
        timer = object : CountDownTimer(timerLength, 1000) {
            override fun onTick(msToFinished: Long) {
                val minutes = msToFinished / 1000 / 60
                val seconds = msToFinished / 1000 % 60
                formatTime(minutes, seconds)

            }

            override fun onFinish() {
                stopTimer()
                formatTime(0, 0)
            }
        }
        timer.start()
    }

    fun formatTime(minutes: Long, seconds: Long): String {
        return "%2d:%2d".format(minutes, seconds)
    }

    fun stopTimer() {
        timer.cancel()
    }
}