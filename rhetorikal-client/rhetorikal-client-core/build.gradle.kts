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
                api(asoft("later-core", vers.asoft.later))
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("test-coroutines", vers.asoft.test))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.testifier,
    description = "An multiplatform representation of a testimony library on the client side"
)