package rhetorikal.witnesses

import rhetorikal.RhetoricianParams
import rhetorikal.witnessService

fun addWitnessManager() {
    println("====".repeat(9))
    print("Enter the witness's name: ")
    val name = readLine()
    println("====".repeat(9))
    print("Enter the witness's title: ")
    val title = readLine()
    println("====".repeat(9))
    print("Enter the witness's workplace: ")
    val workplace = readLine()
    println("====".repeat(9))
    println("Adding $name as a witness, please wait . . .")
    val witnessInfo = RhetoricianParams(
        name = name!!,
        title = title!!,
        workplace = workplace!!
    )
    witnessService.create(witnessInfo).wait()
    println("Added $name successfully")
    println("====".repeat(9))
    println("Press Enter to Continue . . .")
    readLine()
    witnessManager()
}