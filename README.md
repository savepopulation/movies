# movies
An example approach for Android Application modularization and Reactive Clean architecture. 

## Modularization:
This repository is created to demonstrate how to implement __modular android application__ and __reactive clean architecture.__
In __Base__ directory, there are four modules:

```
- core
- core_presentation
- core_domain
- core_data
```

__Core module__ contains classes which can be used in every layer such as injection annotations, injection scopes, error factories,
data holder models. _Core presentation,_ includes core module and classes which can be used in other features presentation modules
such as base ui classes, generic RecyclerView Adapter, ViewModel factories etc. __Core data__ inclues core module and domain spesific
interfaces such as Interactors. Core Data also includes core module, data source interfaces, default request interceptors and 
api module.

All features is implemented as 3 modules which are seperated by their scope.
```
- feature_presentation
- feature_domain
- feature_data
```

__Presentation layer,__ contains, ui classes, injection modules for ui and view entities. Presentation layers includes core_presentation
module. __Domain layer__ containes feature spesific domain objects, interactor implementations and 
repository interace to provide a contract between data and domain layer of feature. __Data layer__ contains core_data module and 
other data related classes such as repository implementaions, remote local data sources etc..

## Tech Stack:
```
- Kotlin
- MVVM
- Clean Architecture 
- Repository Pattern
- RxJava
- Dagger2
- Retrofit
- Architecture Components
- Lifecycle Aware Components
- Modularization
- Unit Testing
- Mockito
- Kotlin DSL
```

## Screenshots:
<img src="https://github.com/savepopulation/movies/blob/master/art/ss1.png"
height="384" width="210">
