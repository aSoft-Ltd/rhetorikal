package rhetorikal

import kotlinx.serialization.Serializable

@Serializable
data class Rhetorician(
    val uid: String,
    val name: String,
    val photo: String?,
    val title: String,
    val workplace: String,
)