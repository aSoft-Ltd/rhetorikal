package rhetorikal

import kotlinx.serialization.Serializable

@Serializable
data class Statement(
    val uid: String,
    val rhetorician: Rhetorician,
    val body: String,
    val status: Status = Status.Allowed
) {
    enum class Status {
        Allowed, Disallowed
    }
}