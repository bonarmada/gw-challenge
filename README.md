# GoodWork

Goodwork is a job-seeker app that uses [The muse API: job boards and company profiles](https://www.themuse.com/developers/api/v2). The criteria when I chose which API to use is (1) public, (2) capable of Pagination. The main features of the app are: 
* Retrieve paginated data of available jobs. 
* Filter through different industries.
* Offline-first data flow via repository pattern
* Save and unsave from favourites.


![gw1](https://user-images.githubusercontent.com/13703275/151246927-e0cb06da-5e1c-4e43-b4b0-f99148e56d8b.png)
![gw2](https://user-images.githubusercontent.com/13703275/151246932-9bc378d8-711b-4576-a21a-9fd6e7fabd4f.png)



# Tech used

MinSdk 23 - [as all practical, and rational developers should use](https://twitter.com/minsdkversion)

#### Persistence
Used repository pattern, simple but clear data flow from remote source to local source to UI. Used Room to persist data.


#### Architecture
App is purely written in kotlin, Used MVVM architectural pattern. SOLID Principles particularly SRP is heavily applied, which makes the app maintainable and very easy to read.


#### UI annd Design
Functional, intuitive and aestethically pleasing design. Originally planned to be minimal and neutral but later on decided to choose my own palette, followed Material Design guidelines such as emphasis on vital information, proper spacing, proper use of shapes and white spaces. Credits to this [uplabs post](https://www.uplabs.com/posts/job-seeker-ui-kit-full) to which I referenced when I was trying to design the app.

[Theming and styling](https://developer.android.com/guide/topics/ui/look-and-feel/themes) is utilized but only to used components. 

#### Dependencies
Most of these are pretty much community standards.

* Hilt and Dagger2 - [Depedency injector](https://developer.android.com/training/dependency-injection/hilt-android) for android.
* RXJava3 - reactive UI, works seemlessly with data emmited by data sources.. Also for concurrency.
* Navigation Components & Safe Args - navigation wrapper when working with fragment transactions. 
* Paging3 - definitely a must for social media and job search apps. This helps to present the ui as logically separated data chunks.
* Retrofit2 & OkHttp - Networking and http client.
* Gson - Mapper tool for json objects.
* Room & ViewModel - used for persistence, also used ViewModels to mannage UI-related data i.e persisted data, view objects. 
* Glide - image loading and caching library.
* Timber - logging


#### TODOs
- :white_check_mark: Persistence
- :white_check_mark: Favorites
- :white_square_button: Unit testing
- :white_square_button: Register to the API and use an API (currently limited to 500 requests/hour)
- :white_square_button: Paging3 + Room
