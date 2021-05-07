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
                api(project(":rhetorikal-core"))
                api(asoft("later-ktx", vers.asoft.later))
                api(asoft("viewmodel-core", vers.asoft.viewmodel))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":rhetorikal-client-ktor"))
                implementation(asoft("test-coroutines", vers.asoft.test))
                implementation(asoft("expect-coroutines", vers.asoft.expect))
                implementation(asoft("viewmodel-test-expect", vers.asoft.viewmodel))
            }
        }

        val jvmTest by getting {
            dependencies {
                api("io.ktor:ktor-client-okhttp:${vers.ktor}")
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.rhetorikal,
    description = "An multiplatform representation of a testimony library on the client side"
)