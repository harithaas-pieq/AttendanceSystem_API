// In AttendanceApp.kt
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.dropwizard.core.Application
import io.dropwizard.core.setup.Environment

class AttendanceApplication : Application<AttendanceConfiguration>() {
    override fun run(config: AttendanceConfiguration, environment: Environment) {
        // Register the Kotlin module explicitly
        environment.getObjectMapper().registerModule(KotlinModule())
        // ObjectMapper handles automatic conversion between Kotlin objects and JSON (serialization/deserialization).
        val resource = AttendanceResource()
        environment.jersey().register(resource)
    }
}

fun main(args: Array<String>) {
    AttendanceApplication().run(*args)
}
