package rhetorikal

import rhetorikal.witnesses.witnessManager

val witnessService = RhetoriciansClientServiceKtor(endpoint = "http://localhost:8081")

fun main() {
    witnessManager()
}