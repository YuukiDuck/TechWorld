plugins {
    id("com.android.application")
}

android {
    namespace = "vnua.k66httt.techworld"
    compileSdk = 34

    defaultConfig {
        applicationId = "vnua.k66httt.techworld"
        minSdk = 26
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
//    implementation("com.google.firebase:firebase-analytics")
//slide
    implementation("com.makeramen:roundedimageview:2.3.0")
    implementation ("me.relex:circleindicator:2.1.6")
    //picaso
    implementation ("com.squareup.picasso:picasso:2.8")
    implementation(kotlin("script-runtime"))

}