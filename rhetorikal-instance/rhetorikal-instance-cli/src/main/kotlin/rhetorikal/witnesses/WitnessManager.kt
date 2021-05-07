package rhetorikal.witnesses

import kotlin.system.exitProcess

fun witnessManager() {
    println("====".repeat(9))
    println("Welcome to Witness Management")
    println("====".repeat(9))
    println("1. View all witnesses")
    println("2. Create a new witness")
    println("3. Delete an existing witness")
    println("0. Exit the witness manager")
    println("====".repeat(9))
    print("Please enter your choice: ")

    when (val choice = readLine()) {
        "1" -> viewWitnesses()
        "2" -> addWitnessManager()
        "3" -> println("You selected to edit a witness")
        "0" -> {
            println("Thank you for your good management")
            exitProcess(0)
        }
        else -> {
            println("====".repeat(9))
            println("Invalid choice")
            witnessManager()
        }
    }
}