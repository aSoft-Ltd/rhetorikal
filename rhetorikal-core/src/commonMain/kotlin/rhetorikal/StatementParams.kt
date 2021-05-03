package rhetorikal

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmOverloads

@Serializable
data class StatementParams @JvmOverloads constructor(
    val rhetoricianId: String,
    val statement: String,
    val status: Statement.Status = Statement.Status.Allowed
)