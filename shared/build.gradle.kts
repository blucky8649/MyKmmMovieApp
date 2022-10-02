plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
    id("com.github.gmazzo.buildconfig") version "3.1.0"
}
group = "com.smp"
version = "1.0-SNAPSHOT"

buildConfig {
    val prop = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
    buildConfigField("String", "CLIENT_ID", "\"${prop.getProperty("naverapi.clientid")}\"")
    buildConfigField("String", "CLIENT_SECRET", "\"${prop.getProperty("naverapi.clientsecret")}\"")
}
kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }
    val sqlDelightVersion: String by project
    val ktorVersion = "1.4.3"
    val coroutinesVersion = "1.5.0"
    val serializationVersion = "1.2.2"
    val koinVersion = "3.2.0"
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
                implementation( "io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")
                implementation("io.insert-koin:koin-core:$koinVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")
            }
        }

        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("io.ktor:ktor-client-logging-native:1.3.1")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)

        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.example.mykmmmovieapp"
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}
sqldelight {
    database("AppDatabase") {
        packageName = "com.example.mykmmmovieapp.db"
    }
}