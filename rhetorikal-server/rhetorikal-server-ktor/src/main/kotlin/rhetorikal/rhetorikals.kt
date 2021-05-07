package rhetorikal

import duality.receiveTextBody
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.routing.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import later.await
import duality.respond

fun Application.rhetorikals() = routing {
    val service = RhetorikalService()
    val prefix = "/api/v1"
    post("$prefix/${Routes.Statements.single}") {
        respond(Statement.serializer()) {
            val body = call.receiveTextBody() ?: badRequest("No request body passed as Testimony params")
            val params = Json.decodeFromString(StatementParams.serializer(), body)
            resolve(service.create(params).await())
        }
    }

    get("$prefix/${Routes.Statements.all}") {
        respond(ListSerializer(Statement.serializer())) {
            resolve(service.statements.all().await())
        }
    }

    post("$prefix/${Routes.Rhetoricians.single}") {
        respond(Rhetorician.serializer()) {
            val body = call.receiveTextBody() ?: badRequest("No Witness data provided")
            val param = Json.decodeFromString(RhetoricianParams.serializer(), body)
            resolve(service.rhetoricians.create(param).await(), OK)
        }
    }

    put("$prefix/${Routes.Rhetoricians.single}") {
        respond(Rhetorician.serializer()) {
            val body = call.receiveTextBody() ?: badRequest("No Witness data provided")
            val param = Json.decodeFromString(Rhetorician.serializer(), body)
            resolve(service.rhetoricians.edit(param).await(), OK)
        }
    }

    get("$prefix/${Routes.Rhetoricians.all}") {
        respond(ListSerializer(Rhetorician.serializer())) {
            resolve(service.rhetoricians.all().await(), OK)
        }
    }
}