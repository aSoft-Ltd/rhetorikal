package rhetorikal

import later.Later

class RhetoriciansService {
    private val rhetoricians = mutableMapOf<String, Rhetorician>()
    fun create(info: RhetoricianParams) = Later<Rhetorician> { resolve, _ ->
        val uid = "witness-${rhetoricians.size + 1}"
        val rhetorician = Rhetorician(
            uid = uid,
            name = info.name,
            photo = null,
            title = info.title,
            workplace = info.workplace
        )
        rhetoricians[uid] = rhetorician
        resolve(rhetorician)
    }

    fun edit(rhetorician: Rhetorician) = Later<Rhetorician> { resolve, reject ->
        val w = rhetoricians[rhetorician.uid]
            ?: return@Later reject(Exception("Witness with uid=${rhetorician.uid} not found"))
        rhetoricians[w.uid] = rhetorician
        resolve(rhetorician)
    }

    fun load(rhetoricianId: String) = Later<Rhetorician> { resolve, reject ->
        val rhetorician = rhetoricians[rhetoricianId]
            ?: return@Later reject(Exception("Witness with id=$rhetoricianId doesn't exist"))
        resolve(rhetorician)
    }

    fun all() = Later.resolve(rhetoricians.values.toList())
}