pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

//dependencyResolutionManagement {
//    versionCatalogs {
//        create("libs") {
//            version("groovy", "3.0.5")
//            version("checkstyle", "8.37")
//            alias("groovy-core").to("org.codehaus.groovy", "groovy").versionRef("groovy")
//            alias("groovy-json").to("org.codehaus.groovy", "groovy-json").versionRef("groovy")
//            alias("groovy-nio").to("org.codehaus.groovy", "groovy-nio").versionRef("groovy")
//            alias("commons-lang3").to("org.apache.commons", "commons-lang3").version {
//                strictly("[3.8, 4.0[")
//                prefer("3.9")
//            }
//        }
//    }
//}

rootProject.name = "SoskaRickyAndMorty"
include(
    ":app",
    ":data",
    ":domain",
)
