import jakarta.ws.rs.* //Brings in JAX-RS annotations
import jakarta.ws.rs.core.Response //Class to build HTTP custom responses.
import model.Attendance
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.ConcurrentHashMap


@Path("/attendance")
class AttendanceResource(
    private val records: MutableList<Attendance>,
    private val activeCheckIns: ConcurrentHashMap<String, Attendance>
    )
    {
    @POST
    @Path("/checkin")
    fun checkIn(request: CheckInDTO): Response { //JAX-RS automatically maps the incoming JSON into a Kotlin object (CheckInDTO) if the request body matches its fields
        if (activeCheckIns.containsKey(request.id)) {
            return Response.status(Response.Status.CONFLICT)
                .entity(  // Attach data to response body
                    mapOf(
                        "status" to "error",
                        "code" to Response.Status.CONFLICT.statusCode,
                        "message" to "Employee with ID ${request.id} is already checked in."
                    )
                )
                .build() //Create response body objects to return
        }

        val newRecord = Attendance(Employeeid = request.id, checkInTime = LocalDateTime.now())
        activeCheckIns[request.id] = newRecord
        records.add(newRecord)

        return Response.status(Response.Status.CREATED).entity(
            mapOf(
                "status" to "success",
                "code" to Response.Status.CREATED.statusCode,
                "message" to "Employee with ID ${request.id} checked in successfully",
                "data" to newRecord
            )
        ).build()
    }

    @POST
    @Path("/checkout")
    fun checkOut(request: CheckOutDTO): Response {
        val activeRecord = activeCheckIns.remove(request.id)

        return if (activeRecord != null) {
            val checkOutTime = LocalDateTime.now()
            activeRecord.checkOutTime = checkOutTime

            val totalMinutes = ChronoUnit.MINUTES.between(activeRecord.checkInTime, checkOutTime)
            val hours = totalMinutes / 60
            val minutes = totalMinutes % 60
            activeRecord.workingTime = totalMinutes

            return Response.status(Response.Status.OK).entity(
                mapOf(
                    "status" to "success",
                    "code" to Response.Status.OK.statusCode,
                    "message" to "Employee with ID ${request.id} checked out successfully",
                    "data" to activeRecord
                )
            ).build()
        } else {
            Response.status(Response.Status.NOT_FOUND)
                .entity(
                    mapOf(
                        "status" to "error",
                        "code" to Response.Status.NOT_FOUND.statusCode,
                        "message" to "No active check-in found for employee ID ${request.id}."
                    )
                )
                .build()
        }
    }
    @GET
    fun getAllAttendance(): Response {
        return Response.status(Response.Status.OK).entity(
            mapOf(
                "status" to "success",
                "code" to Response.Status.OK.statusCode,
                "message" to "All attendance records retrieved successfully",
                "data" to records //returns all the records from the service.AttendanceList
            )
        ).build()
    }
}

// DataTransferObjects(DTOs)
// It define the structure of data exchanged in API requests and responses.
data class CheckInDTO(val id: String)
data class CheckOutDTO(val id: String)