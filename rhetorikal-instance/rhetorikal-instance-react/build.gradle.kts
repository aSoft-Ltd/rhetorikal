plugins {
    kotlin("js")
    id("tz.co.asoft.applikation")
}

applikation {
    debug()
}

kotlin {
    js(IR) {
        browserApp()
    }
    sourceSets {
        val main by getting {
            dependencies {
                implementation(project(":rhetorikal-client-ktor"))
                implementation(asoft("reakt-feedback", vers.asoft.reakt))
                implementation(asoft("reakt-form", vers.asoft.reakt))
                implementation(asoft("reakt-buttons", vers.asoft.reakt))
                implementation(asoft("reakt-text", vers.asoft.reakt))
                implementation(asoft("viewmodel-react", vers.asoft.viewmodel))
            }
        }
    }
}