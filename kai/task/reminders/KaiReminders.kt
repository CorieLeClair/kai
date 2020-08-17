package kai.task.reminders

import java.text.SimpleDateFormat
import java.util.*


class KaiReminders() {
    fun initReminder(function: () -> Unit, date : String) {
        val t = Timer()

        t.schedule(object : TimerTask() {
            override fun run() {
                function()
            }
        }, SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date))
    }
}