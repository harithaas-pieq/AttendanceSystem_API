import java.time.LocalDateTime
import com.fasterxml.jackson.annotation.JsonFormat
data class Attendance(
    val Employeeid: String,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val checkInTime: LocalDateTime,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    var checkOutTime: LocalDateTime? = null,
    var workingTime: Long? = null
)