plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    target { library() }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":rhetorikal-server-core"))
                api(project(":rhetorikal-rest"))
                api(asoft("result-ktor", vers.asoft.duality))
                api("io.ktor:ktor-server-core:${vers.ktor}")
            }
        }

        val test by getting {
            dependencies {
                api(asoft("test-coroutines", vers.asoft.test))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.rhetorikal,
    description = "A Ktor lib that manage testimonials"
)