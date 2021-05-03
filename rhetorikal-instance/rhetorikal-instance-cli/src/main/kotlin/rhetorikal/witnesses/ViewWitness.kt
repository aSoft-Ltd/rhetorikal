package rhetorikal.witnesses

import rhetorikal.witnessService

fun viewWitnesses() {
    val witnesses = witnessService.all().wait()
    if (witnesses.isEmpty()) {
        println("There are no witnesses in the system")
    } else {
        for (witness in witnesses) {
            println("${witness.name}\t\t${witness.title}\t\t${witness.workplace}")
        }
    }
    witnessManager()
}