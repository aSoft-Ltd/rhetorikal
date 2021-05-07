package rhetorikal.services

interface RhetorikalClientService {
    val rhetoricians: RhetoriciansClientService
    val statements: StatementsClientService
}