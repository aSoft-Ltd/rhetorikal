package rhetorikal

import later.Later

interface StatementsClientService {
    fun create(params: StatementParams): Later<Statement>
    fun edit(statement: Statement): Later<Statement>
    fun delete(testimonyId: String): Later<Statement>
    fun all(): Later<List<Statement>>
}