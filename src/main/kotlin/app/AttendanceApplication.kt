// In AttendanceApp.kt
import com.fasterxml.jackson.module.kotlin.KotlinModule
import configg.AttendanceConfiguration
import io.dropwizard.core.Application
import io.dropwizard.core.setup.Environment
import model.Attendance
import resource.AttendanceResource
import java.util.concurrent.ConcurrentHashMap

class AttendanceApplication : Application<AttendanceConfiguration>() {
    override fun run(config: AttendanceConfiguration, environment: Environment) {
        // Register the Kotlin module explicitly
        environment.getObjectMapper().registerModule(KotlinModule())
        // ObjectMapper handles automatic conversion between Kotlin objects and JSON (serialization/deserialization).
        val records = arrayListOf<Attendance>()
        val activeCheckIns = ConcurrentHashMap<String, Attendance>()
        val resource = AttendanceResource(records, activeCheckIns)
        environment.jersey().register(resource)
    }
}

fun main(args: Array<String>) {
    AttendanceApplication().run(*args)
}