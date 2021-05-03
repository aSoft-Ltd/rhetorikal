package rhetorikal

object Routes {
    const val root = "rhetorikal"

    object Rhetoricians {
        const val all = "/$root/rhetoricians"
        const val single = "/$root/rhetorician"
    }

    object Statements {
        const val all = "/$root/statements"
        const val single = "/$root/statement"
    }
}