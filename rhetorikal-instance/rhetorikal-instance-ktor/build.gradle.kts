plugins {
    kotlin("plugin.serialization")
    kotlin("jvm")
    id("tz.co.asoft.applikation")
}

application {
    mainClass.set("rhetorikal.MainKt")
}

applikation {
    release()
    debug()
}

kotlin {
    target { application() }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":rhetorikal-server-ktor"))
                api("io.ktor:ktor-server-cio:${vers.ktor}")
            }
        }

        val test by getting {
            dependencies {
                api(asoft("test-coroutines", vers.asoft.test))
            }
        }
    }
}