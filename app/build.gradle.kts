plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
//    kotlin("android")
    alias(libs.plugins.google.dagger.hilt)
//    id("kotlin-parcelize")
//    alias(libs.plugins.kotlin-parcelize)
}

android {
    namespace = "com.firstbit.composeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.firstbit.composeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.google.dagger.hilt)

    // Compose dependencies
    implementation(libs.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // ViewModel and LiveData
    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Room
//    implementation (libs.androidx.room.runtime)
//    implementation (libs.androidx.room.ktx)
//    annotationProcessor (libs.androidx.room.compiler)

    // Retrofit for API calls
    implementation (libs.retrofit2.retrofit)
    implementation (libs.converter.gson)

    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")
    implementation(composeBom)

    debugImplementation(libs.ui.tooling)
    implementation(libs.ui.tooling.preview)
    implementation (libs.androidx.runtime.livedata)

    kapt(libs.google.dagger.hilt.compiler)

    implementation (libs.androidx.room.ktx)
    implementation (libs.androidx.room.runtime)
    annotationProcessor (libs.androidx.room.compiler)
    kapt (libs.androidx.room.compiler)


    testImplementation(libs.junit)
    // Mockito
    testImplementation (libs.mockito.core.v481)
    testImplementation (libs.mockito.kotlin)

    // MockK for advanced Kotlin mocking
    testImplementation (libs.mockk)
    testImplementation (libs.mockito.kotlin)

    // LiveData testing utilities
    testImplementation (libs.androidx.core.testing)

    // Coroutines testing utilities
    testImplementation (libs.kotlinx.coroutines.test.v181)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}