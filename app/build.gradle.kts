plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.soskarikcyandmorty"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    // View Binding
    buildFeatures.viewBinding = true
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation ("androidx.core:core-ktx:1.6.0")
    implementation ("androidx.appcompat:appcompat:1.3.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    // glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    val hiltVersion = "2.41"
    // Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    // hilt navigation
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    val activityVersion = "1.3.0"
    // Kotlin
    implementation("androidx.activity:activity-ktx:$activityVersion")

    val fragmentVersion = "1.3.6"
    implementation("androidx.fragment:fragment-ktx:$fragmentVersion")

    // Navigation
    val navVersion = "2.4.1"
    //   implementation
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")

    //Room
    val roomVersion = "2.3.0"
    implementation("androidx.room:room-ktx:$roomVersion")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$roomVersion")

    // Paging 3
    val pagingVersion = "3.0.1"
    //noinspection GradleDependency
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")

    // ViewBinding Property Delegate
    val viewBindingPropertyDelegate = "1.4.7"
    // To use only without reflection variants of viewBinding
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:$viewBindingPropertyDelegate")

    val version = "2.4.1"
    // | for Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$version")

    // coordinatorLayout
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.1.0")

    // Material Design Components
    implementation("com.google.android.material:material:1.5.0")

    // lifecycle runtime
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

    // CircleImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")

}