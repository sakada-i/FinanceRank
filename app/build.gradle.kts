plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.gms.google-services")

    // Kapt
//    kotlin("kapt")
    // Hilt-Dagger
//    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.financerank"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.financerank"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
//        jvmTarget = "11"
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
//    implementation("androidx.navigation:navigation-compose-jvmstubs:2.9.3")
    implementation("com.google.firebase:firebase-firestore-ktx:25.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:34.1.0"))
    implementation("com.google.firebase:firebase-analytics")
    // Firebase.Authentication
    implementation("com.google.firebase:firebase-auth")
    // デバッグ用
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.compose.material:material-icons-extended")
    // ViewModel用
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")
    // Hilt-Dagger
//    implementation("com.google.dagger:hilt-android:2.51.1")
    // Kapt
//    kapt("com.google.dagger:hilt-compiler:2.51.1")

//    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.9.0")
    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.9.3")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.2")
    // Flow と ViewModelScope の連携に必要
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    // Compose から viewModel() を呼ぶのに必要
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")
    // collectAsStateWithLifecycle を使う場合に必要
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.2")
}