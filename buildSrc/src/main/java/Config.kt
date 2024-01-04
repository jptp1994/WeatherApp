object Config {

    object Android {
        // Android sdk and version
        const val androidMinSdkVersion = 26
        const val androidTargetSdkVersion = 34
        const val androidCompileSdkVersion = 34
        const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    object Plugins {
        const val android = "com.android.application"
        const val hiltAndroid= "com.google.dagger.hilt.android"
        const val kapt= "kapt"
        const val hiltPlugin="dagger.hilt.android.plugin"
        const val kotlinAndroidPlug="kotlin-android"
        const val kotlinAndroid= "org.jetbrains.kotlin.android"
    }

}