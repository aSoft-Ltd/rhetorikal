package rhetorikal

import later.Later

class StatementsService {
    private val statements = mutableMapOf<String, Statement>()
    fun create(params: StatementParams, rhetorician: Rhetorician): Later<Statement> = Later { resolve, reject ->
        val uid = "statement-${statements.size + 1}"
        val statement = Statement(
            uid = uid,
            rhetorician = rhetorician,
            body = params.statement,
            status = params.status
        )
        statements[uid] = statement
        resolve(statement)
    }

    fun all() = Later<List<Statement>> { resolve, reject ->
        resolve(statements.values.toList())
    }
}