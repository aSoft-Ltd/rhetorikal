package rhetorikal

import later.Later

interface RhetoriciansClientService {
    fun create(params: RhetoricianParams, photo: ByteArray? = null): Later<Rhetorician>
    fun edit(rhetorician: Rhetorician): Later<Rhetorician>
    fun all(): Later<List<Rhetorician>>
}