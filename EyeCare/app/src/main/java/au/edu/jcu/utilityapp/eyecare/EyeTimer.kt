package au.edu.jcu.utilityapp.eyecare

class EyeTimer {

    var minutes: Int = 20
    var seconds: Int = 20
    var isRunning: Boolean = false

    fun startTimer() {
        isRunning = true
        runTimer()
    }

    /**
     * Timer stops
     * Minutes and seconds go to 0.
     */
    fun cancelTimer() {
        isRunning = false
        minutes = 0
        seconds = 0
    }

    fun setTimer(mins: Int, secs: Int) {
        minutes = mins
        seconds = secs
    }

    fun runTimer() {

    }
}