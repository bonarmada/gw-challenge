# gw-challenge

MinSdk 23 - [as all practical, and rational developers should use](https://twitter.com/minsdkversion)

#### Persistence
Used repository pattern, simple but clear data flow from remote source to UI. Used Room and SharedPreferences to persists data.


#### Architecture
App is purely written in kotlin, Used MVVM architectural pattern


#### UI annd Design
Functional, intuitive and aestethically pleasing design. Originally planned to be minimal and neutral but later on decided to choose my own palette, followed Material Design guidelines such as emphasis on vital information, proper spacing, proper use of shapes and white spaces.


#### Dependencies
Most of these are pretty much community standards.

* Hilt and Dagger2 - [Depedency injector](https://developer.android.com/training/dependency-injection/hilt-android) for android.
* RXJava3 - reactive UI, works seemlessly with data emmited by data sources.. Also for concurrency.
* Navigation Components & Safe Args - navigation wrapper when working with fragment transactions. 
* Paging3 - definitely a must for social media and job search apps. This helps to present the ui as logically separated data chunks.
* Retrofit2 & OkHttp - Networking and http client.
* Gson - Mapper tool for json objects.
* Room & ViewModel - used for persistence, also used ViewModels to mannage UI-related data i.e persisted data, view objects. 

