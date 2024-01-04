import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroidPlug)
    id(Config.Plugins.hiltPlugin)
    kotlin(Config.Plugins.kapt)

}

android {
    namespace = Environments.Release.appId
    compileSdk = Config.Android.androidCompileSdkVersion

    defaultConfig {
        applicationId = Environments.Release.appId
        minSdk = Config.Android.androidMinSdkVersion
        targetSdk = Config.Android.androidTargetSdkVersion
        versionCode = Environments.Release.appVersionCode
        versionName = Environments.Release.appVersionName
        multiDexEnabled= true
        testInstrumentationRunner = Config.Android.testRunner
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Core
    implementation(Dependencies.CoreDep.coreKtx)
    implementation(Dependencies.LifeCycleDep.lifeCycleRuntimeKtx)

    //Compose
    implementation(Dependencies.ComposeDep.ui)
    implementation(platform(Dependencies.ComposeDep.composeBom))
    implementation(Dependencies.ComposeDep.activityUI)
    implementation(Dependencies.ComposeDep.uiGraphics)
    implementation(Dependencies.ComposeDep.uiPreview)
    implementation(Dependencies.ComposeDep.uiMaterial)
    implementation(Dependencies.CoreDep.ar)
    implementation(Dependencies.ComposeDep.liveData)

    //Google Maps
    implementation(Dependencies.GoogleDep.maps)

    //Unit Test
    testImplementation(Dependencies.TestDep.junit)
    implementation(Dependencies.TestDep.junitKtx)
    androidTestImplementation(Dependencies.TestDep.testExtJunit)
    androidTestImplementation(Dependencies.TestDep.espressoCore)
    androidTestImplementation(platform(Dependencies.ComposeDep.composeBom))
    androidTestImplementation(Dependencies.ComposeDep.junit)
    debugImplementation(Dependencies.ComposeDep.tooling)
    debugImplementation(Dependencies.ComposeDep.uiManifest)
    testImplementation(Dependencies.TestDep.robolectric)
    testImplementation(Dependencies.TestDep.coroutinesTest)
    testImplementation(Dependencies.TestDep.mockitoKotlin)

    //Room
    implementation(Dependencies.RoomDep.room)
    implementation(Dependencies.RoomDep.roomKtx)
    kapt(Dependencies.RoomDep.roomCompiler)

    // optional - Test helpers
    testImplementation(Dependencies.TestDep.roomTest)

    //Hilt
    implementation(Dependencies.DaggerHiltDep.hiltAndroid )
    kapt(Dependencies.DaggerHiltDep.hiltAndroidKapt )

    //Retrofit and okHttp
    implementation(Dependencies.RetrofitDep.loggingInterceptor)
    implementation(Dependencies.RetrofitDep.retrofit)
    implementation(Dependencies.RetrofitDep.retrofitConverter)
    implementation(Dependencies.RetrofitDep.moshiConverter)

}

kapt {
    correctErrorTypes = true
}