plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    // Javax Inject
    api("javax.inject:javax.inject:1")

    // Kotlin
    // | Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
}