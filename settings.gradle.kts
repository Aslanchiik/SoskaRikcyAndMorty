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

    rootProject.name = "SoskaRickyAndMorty"
    include(
        ":app",
        ":data",
        ":domain",
    )
}
//    enableFeaturePreview("VERSION_CATALOGS")


//    dependencyResolutionManagement {
//        versionCatalogs {
//            create("libs") {
//                version("retrofit", "2.9.0")
//
//                alias("retrofit-base").to("com.squareup.retrofit2", "retrofit").versionRef("retrofit")
//                alias("retrofit-moshi").to("com.squareup.retrofit2", "converter-moshi").versionRef("retrofit")
//                alias("retrofit-rx").to("com.squareup.retrofit2", "adapter-rxjava2").versionRef("retrofit")
//                bundle("retrofit", listOf("retrofit-base", "retrofit-moshi", "retrofit-rx"))
//            }
//        }
//    }
