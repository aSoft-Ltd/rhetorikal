package rhetorikal.witnesses

import rhetorikal.witnessService

fun viewWitnesses() {
    println("====".repeat(9))
    println("Loading all witnesses, please wait . . .")
    val witnesses = witnessService.all().wait()
    if (witnesses.isEmpty()) {
        println("There are no witnesses in the system")
    } else {
        println("No.\tName\t\tTitle\t\tWorkplace")
        witnesses.forEachIndexed { index, witness ->
            println("${index + 1}\t${witness.name}\t\t${witness.title}\t\t${witness.workplace}")
        }
    }
    println("====".repeat(9))
    println("Press Enter to Continue . . .")
    readLine()
    witnessManager()
}