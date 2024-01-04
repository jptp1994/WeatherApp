import Versions

//Contains the dependencies of the proyect
object Dependencies {

    object CoreDep {
        const val ar= "com.google.ar:core:1.41.0"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    }

    object ComposeDep{
        const val activityUI= "androidx.activity:activity-compose:1.8.2"
        const val composeBom= "androidx.compose:compose-bom:2023.03.00"
        const val junit= "androidx.compose.ui:ui-test-junit4"
        const val liveData= "androidx.compose.runtime:runtime-livedata:1.5.4"
        const val tooling= "androidx.compose.ui:ui-tooling"
        const val ui= "androidx.compose.ui:ui"
        const val uiGraphics= "androidx.compose.ui:ui-graphics"
        const val uiManifest= "androidx.compose.ui:ui-test-manifest"
        const val uiMaterial= "androidx.compose.material3:material3"
        const val uiPreview= "androidx.compose.ui:ui-tooling-preview"
    }

    object CoroutinesDep {
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object DaggerHiltDep {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
        const val hiltAndroidKapt = "com.google.dagger:hilt-compiler:${Versions.daggerHiltCompiler}"

    }
    object GoogleDep{
       const val maps="com.google.android.gms:play-services-maps:18.2.0"
    }

    object LifeCycleDep {
        const val lifeCycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleViewModel}"
    }

    object RetrofitDep {
        const val retrofit="com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverter="com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverterVersion}"
        const val loggingInterceptor= "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogger}"
    }

    object RoomDep {
        const val room= "androidx.room:room-runtime:${Versions.room}"
        const val roomKtx= "androidx.room:room-ktx:${Versions.room}"
        const val roomCompiler="androidx.room:room-compiler:${Versions.room}"
    }

    object TestDep {

        const val coroutinesTest="org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val espressoCore="androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val junit= "junit:junit:${Versions.junit}"
        const val junitKtx= "androidx.test.ext:junit-ktx:${Versions.junitExt}"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}"

        const val roomTest = "androidx.room:room-testing:${Versions.room}"
        const val testExtJunit= "androidx.test.ext:junit:${Versions.junitExt}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"

    }


}