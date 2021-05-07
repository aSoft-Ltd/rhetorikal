plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    val darwinTargets = listOf(
        macosX64(),
        iosArm32(),
        iosX64(),
        iosArm64(),
        watchosArm64(),
        watchosArm32(),
        watchosX86(),
        tvosArm64(),
        tvosX64()
    )

    val linuxTargets = listOf(
        linuxX64()
    )

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":rhetorikal-client-core"))
                api(project(":rhetorikal-rest"))
                api("io.ktor:ktor-client-core:${vers.ktor}")
                api(asoft("later-ktx", vers.asoft.later))
                api(asoft("result-core", vers.asoft.duality))
            }
        }

        val jvmTest by getting {
            dependencies {
                api("io.ktor:ktor-client-okhttp:${vers.ktor}")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.rhetorikal,
    description = "An multiplatform representation of a testimony library on the client side"
)