# Weather App test
Weather App test is an app built with [Kotlin][1] and Room and retrofit for networking, displays data in real-time using [Android Architecture Components][3] and the MVVM Architecture Pattern. 
For the UI it uses jetpack compose [Jetpack Compose][2] and navigation safe args to pass the data between the screens, Android's modern toolkit for building native UI.

Show a default data inserted in room table, and every time a search is sended a new register is created if not exist in the database.

It have some extension functions in the string to format the information of the api. [Extension Functions][9] and use case to help the clean architecture component [Extension Functions][10] 

And kotlin dsl for get the versions, and all the configuration of the proyect gradle [Kotlin Dsl][11]

Below you can find the docs for each tehnology that is used in this app:

## Jetpack Compose:
* [JetpackCompose][2]

## Android Architecture Components:
* [LiveData][4]
* [ViewModel][5]

## Dependency Injection:
* [Hilt for Android][6]

## Asynchronous Programming:
* [Kotlin Coroutines][7]
* [Asynchronous Flow][8]

## Gradle DSL

* [Dynamic adding components libraries][11]

## Other Android Components:
* [Modular proyect with clean architecture][12]

[1]: https://kotlinlang.org/
[2]: [https://firebase.google.com/docs/firestore](https://medium.com/gradeup/jetpack-compose-everything-you-need-to-know-to-get-started-2db13d248b0a)
[3]: https://developer.android.com/topic/libraries/architecture
[4]: https://developer.android.com/topic/libraries/architecture/livedata
[5]: https://developer.android.com/topic/libraries/architecture/viewmodel
[6]: https://developer.android.com/training/dependency-injection/hilt-android
[7]: https://kotlinlang.org/docs/coroutines-overview.html
[8]: https://kotlinlang.org/docs/flow.html
[9]: [https://kotlinlang.org/docs/flow.html](https://www.baeldung.com/kotlin/extension-methods)
[10]: [https://kotlinlang.org/docs/flow.html](https://developermemos.com/posts/usecase-pattern-kotlin)

[11]: https://www.youtube.com/watch?v=w5qCmvS9JGE
[12]: https://ahmad-efati.medium.com/modularization-of-android-applications-based-on-clean-architecture-18dc643e0562
