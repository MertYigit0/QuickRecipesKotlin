plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs")


}

android {
    namespace = "com.mertyigit0.quickrecipeskotlin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mertyigit0.quickrecipeskotlin"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    val nav_version = "2.7.2"
    val roomVersion = "2.5.2"
    val  lifecycle_version = "2.6.1"
    val retrofitVersion = "2.9.0"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



    // Navigation
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Room
    implementation ("androidx.room:room-runtime:$roomVersion")
    annotationProcessor ("androidx.room:room-compiler:$roomVersion")
    ksp ("androidx.room:room-compiler:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")

    //RXjava
    implementation ("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    //Palette
    implementation ("androidx.palette:palette-ktx:1.0.0")

    //Preference
    implementation ("androidx.preference:preference-ktx:1.2.0")



}