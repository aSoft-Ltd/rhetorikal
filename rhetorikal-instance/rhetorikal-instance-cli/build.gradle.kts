plugins {
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
                api(project(":rhetorikal-client-ktor"))
                api("io.ktor:ktor-client-okhttp:${vers.ktor}")
            }
        }

        val test by getting {
            dependencies {
                api(asoft("test-coroutines", vers.asoft.test))
            }
        }
    }
}