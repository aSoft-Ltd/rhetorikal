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
                api(kotlinx("coroutines-core", vers.kotlinx.coroutines))
                api(kotlinx("serialization-core", vers.kotlinx.serialization))
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
    version = vers.asoft.rhetorikal,
    description = "An multiplatform implementation of a testimony library"
)