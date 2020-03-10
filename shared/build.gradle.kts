import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("kotlin-android-extensions")
    id("com.squareup.sqldelight")
//    id ("kotlinx-serialization")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(29)
    }

    sourceSets {
        getByName("main").manifest.srcFile("src/androidMain/AndroidManifest.xml")
    }

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"

    kotlinOptions.freeCompilerArgs += listOf("-Xuse-experimental=kotlin.Experimental")

}

kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    android()

    val coroutinesVersion = "1.3.3"
    val sqldelightVersion = "1.2.2"
    val serializationVersion = "0.20.0"
    val napierVersion = "1.2.0"

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")

        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutinesVersion")

        // Database
        implementation("com.squareup.sqldelight:runtime:$sqldelightVersion")
        implementation("com.squareup.sqldelight:sqlite-driver:$sqldelightVersion")

        // Di
        implementation("org.kodein.di:kodein-di-erased:6.5.0")

        // Utils
        implementation("com.github.aakira:napier:$napierVersion")
//
//        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serializationVersion")
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")

        // Coroutines
        api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

        // Database
        implementation("com.squareup.sqldelight:android-driver:$sqldelightVersion")
        implementation("com.squareup.sqldelight:coroutines-extensions-jvm:$sqldelightVersion")

        // Utils
        api("com.github.aakira:napier-android:$napierVersion")

//        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
    }

    sourceSets["iosMain"].dependencies {
        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3")

        // Database
        implementation("com.squareup.sqldelight:native-driver:$sqldelightVersion")

        // Utils
//        implementation("com.github.aakira:napier-ios:$napierVersion")
//        implementation("com.github.aakira:napier-iosX64:$napierVersion")
//        implementation("com.github.aakira:napier-iosArm32:$napierVersion")
//        implementation("com.github.aakira:napier-iosArm64:$napierVersion")

//        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serializationVersion")
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"

    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)

    inputs.property("mode", mode)

    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText(
            "#!/bin/bash\n"
                    + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                    + "cd '${rootProject.rootDir}'\n"
                    + "./gradlew \$@\n"
        )
        gradlew.setExecutable(true)
    }
}

sqldelight {
    database("Database") {
        packageName = "tat.mukhutdinov.kittygram"
    }
}

tasks.getByName("build").dependsOn(packForXcode)