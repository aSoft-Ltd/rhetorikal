pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

rootProject.name = "rhetorikal"
include(":rhetorikal-core")

include(":rhetorikal-rest")

//client
include(":rhetorikal-client-core")
project(":rhetorikal-client-core").projectDir = File("rhetorikal-client/rhetorikal-client-core")
include(":rhetorikal-client-ktor")
project(":rhetorikal-client-ktor").projectDir = File("rhetorikal-client/rhetorikal-client-ktor")

// server
include(":rhetorikal-server-core")
project(":rhetorikal-server-core").projectDir = File("rhetorikal-server/rhetorikal-server-core")
include(":rhetorikal-server-ktor")
project(":rhetorikal-server-ktor").projectDir = File("rhetorikal-server/rhetorikal-server-ktor")

// instances
include(":rhetorikal-instance-ktor")
project(":rhetorikal-instance-ktor").projectDir = File("rhetorikal-instance/rhetorikal-instance-ktor")

// instances
include(":rhetorikal-instance-cli")
project(":rhetorikal-instance-cli").projectDir = File("rhetorikal-instance/rhetorikal-instance-cli")

include(":rhetorikal-instance-react")
project(":rhetorikal-instance-react").projectDir = File("rhetorikal-instance/rhetorikal-instance-react")
