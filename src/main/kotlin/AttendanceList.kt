import java.util.concurrent.ConcurrentHashMap

class AttendanceList {
    companion object {
        val records = arrayListOf<Attendance>()
        val activeCheckIns = ConcurrentHashMap<String, Attendance>()
    }
}

//the companion object is used to share the data across the project
// that way data stays in memory for the whole application run
// instead of resetting every time you create a new AttendanceList object