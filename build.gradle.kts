// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1")
        // Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
