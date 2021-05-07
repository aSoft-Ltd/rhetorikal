package rhetorikal.services

import later.Later
import rhetorikal.Rhetorician
import rhetorikal.RhetoricianParams

interface RhetoriciansClientService {
    fun create(params: RhetoricianParams, photo: ByteArray? = null): Later<Rhetorician>
    fun edit(rhetorician: Rhetorician): Later<Rhetorician>
    fun all(): Later<List<Rhetorician>>
}