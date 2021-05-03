package rhetorikal

import duality.Result
import duality.parse
import duality.response
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import later.Later
import later.later

class StatementsClientServiceKtor(
    private val endpoint: String,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
    private val client: HttpClient = HttpClient { }
) : StatementsClientService {
    override fun create(params: StatementParams): Later<Statement> = scope.later {
        val json = client.post<String>("$endpoint${Routes.Statements.single}") {
            body = TextContent(
                text = Json.encodeToString(StatementParams.serializer(), params),
                contentType = ContentType.Application.Json
            )
        }
        Result.parse(Statement.serializer(), json).response()
    }

    override fun edit(statement: Statement): Later<Statement> {
        TODO()
    }

    override fun delete(testimonyId: String): Later<Statement> {
        TODO()
    }

    override fun all(): Later<List<Statement>> = scope.later {
        val json = client.get<String>("$endpoint${Routes.Statements.all}")
        Result.parse(ListSerializer(Statement.serializer()), json).response()
    }
}