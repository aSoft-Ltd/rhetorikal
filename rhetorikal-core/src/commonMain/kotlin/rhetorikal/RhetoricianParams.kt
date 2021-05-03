package rhetorikal

import kotlinx.serialization.Serializable

@Serializable
data class RhetoricianParams(
    val name: String,
    val title: String,
    val workplace: String,
)