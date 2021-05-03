package rhetorikal

import duality.Result
import duality.parse
import duality.response
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import later.Later
import later.later

class RhetoriciansClientServiceKtor(
    private val endpoint: String,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
    private val client: HttpClient = HttpClient { },
) : RhetoriciansClientService {
    private val prefix = "/api/v1"
    override fun create(params: RhetoricianParams, photo: ByteArray?) = scope.later {
        val json = client.post<String>("$endpoint$prefix${Routes.Rhetoricians.single}") {
            body = TextContent(
                text = Json.encodeToString(RhetoricianParams.serializer(), params),
                contentType = ContentType.Application.Json
            )
        }
        Result.parse(Rhetorician.serializer(), json).response()
    }

    override fun edit(rhetorician: Rhetorician): Later<Rhetorician> = scope.later {
        val json = client.put<String>("$endpoint$prefix${Routes.Rhetoricians.single}") {
            body = TextContent(
                text = Json.encodeToString(Rhetorician.serializer(), rhetorician),
                contentType = ContentType.Application.Json
            )
        }
        Result.parse(Rhetorician.serializer(), json).response()
    }

    fun delete(witnessId: String): Later<Rhetorician> {
        TODO()
    }

    override fun all(): Later<List<Rhetorician>> = scope.later {
        val json = client.get<String>("$endpoint$prefix${Routes.Rhetoricians.all}")
        Result.parse(ListSerializer(Rhetorician.serializer()), json).response()
    }
}