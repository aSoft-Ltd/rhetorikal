package rhetorikal

import duality.respond
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.serialization.Serializable

@Serializable
data class Status(
    val healthy: Boolean,
    val auth: String?,
    val headers: Map<String, List<String>>
)

fun main() {
    embeddedServer(CIO, port = 3000) {
        install(CORS) {
            header("*")
            allowNonSimpleContentTypes = true
            anyHost()
        }
        rhetorikals()
        routing {
            get("/status") {
                val headers = call.request.headers.entries().associate { it.toPair() }
                val auth = call.request.headers[HttpHeaders.Authorization]
                respond(Status.serializer()) {
                    resolve(Status(healthy = true, auth = auth, headers = headers))
                }
            }
        }
    }.start(wait = true)
}