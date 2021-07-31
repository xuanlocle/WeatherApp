# Weather Application
Android weather application implemented using the MVVM pattern, Kodein, Retrofit , LiveData, ViewModel, Coroutines, Room. WeatherApp fetches data from the [OpenWeatherMap API](https://openweathermap.org/api) to provide real time weather information. 

# Features

- [x] Landing weather list
- [x] Landing search city weather
- [x] Filter query weather
- [x] Handle error message
- [x] Save local database 
- [x] History search list, 
- [x] Secure apk by proguard, 
- [ ] View weather details
- [ ] Improve UX/UI
- [ ] Add favourites cities
- [ ] Notification daily weather

## Architecture
The architecture of this application relies and complies with the following points below:
* Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)(MVVM) which facilitates a separation of development of the graphical user interface.
* [Android architecture components](https://developer.android.com/topic/libraries/architecture/) which help to keep the application robust, testable, and maintainable.

<p align="center"><a><img src="https://raw.githubusercontent.com/mayokunthefirst/Instant-Weather/master/media/final-architecture.png" width="700"></a></p>

## Technologies used:

* [Retrofit](https://square.github.io/retrofit/) a REST Client for Android which makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice.
* [Kodein](https://kodein.org/di/) for dependency injection.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and manage UI-related data in a lifecycle conscious way.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) to handle data in a lifecycle-aware fashion.
* [Material Design](https://material.io/develop/android/docs/getting-started/) an adaptable system of guidelines, components, and tools that support the best practices of user interface design.
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) used to manage the local storage i.e. `writing to and reading from the database`. Coroutines help in managing background threads and reduces the need for callbacks.
* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library which provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [Android KTX](https://developer.android.com/kotlin/ktx) which helps to write more concise, idiomatic Kotlin code.

## Installation
WeatherApp requires a minimum API level of 23. Clone the repository. You will need an API key i.e. `API_KEY` from [Open Weather](https://openweathermap.org/) to request data. If you don’t already have an account, you will need to create one in order to request an API Key. 

In your project's root directory, inside the `local.properties` file (create one if unavailable) include the following lines:

````properties
API_KEY = "YOUR_API_KEY"

````

## Contribution
All contributions are welcome. If you are interested in seeing a particular feature implemented in this app, please open a new issue after which you can make a PR!

## LICENSE
```
MIT License

Copyright (c) 2021 xuanlocle

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
