package rhetorikal.services

import later.Later
import rhetorikal.Statement
import rhetorikal.StatementParams

interface StatementsClientService {
    fun create(params: StatementParams): Later<Statement>
    fun edit(statement: Statement): Later<Statement>
    fun delete(testimonyId: String): Later<Statement>
    fun all(): Later<List<Statement>>
}