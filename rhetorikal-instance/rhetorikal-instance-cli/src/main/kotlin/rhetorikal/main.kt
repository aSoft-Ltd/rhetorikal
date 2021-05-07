package rhetorikal

import kotlinx.coroutines.runBlocking
import rhetorikal.services.RhetoriciansClientServiceKtor
import rhetorikal.witnesses.witnessManager

val witnessService = RhetoriciansClientServiceKtor(endpoint = "http://localhost:8081")

fun main() = runBlocking {
    witnessManager()
}