# StarWars API Tool

![image](https://github.com/AltynZhanbyr/StarWarsAPITool/assets/75243205/1b68bcf8-a42e-4c8b-967d-1c406601ad76)

The Star Wars Search App is a simple application that allows you to search for characters and starships from the Star Wars universe. The app is built using Jetpack Compose and follows the MVVM (Model-View-ViewModel) clean architecture pattern. It utilizes the swapi.dev API as the data source for Star Wars-related information and implements a Room Database to store and manage your favorite characters and starships.

## Features
Search for Star Wars characters and starships.
View detailed information about each character and starship.
Easily navigate through search results.
Add characters and starships to the Favorites panel.
View and manage your favorite characters and starships in the Favorites panel.

## Architecture
The Star Wars Search App follows the MVVM (Model-View-ViewModel) clean architecture pattern, which provides a clear separation of concerns and enhances testability and maintainability. The key components of the architecture are:

Model: Represents the data and business logic of the application. It handles data retrieval from the API and interacts with the local database.
View: Represents the UI of the application. It uses Jetpack Compose to create a declarative and reactive user interface.
ViewModel: Acts as a bridge between the Model and View. It holds and manages the application's state and exposes data to the View.

## Dependencies
The Star Wars Search App relies on the following dependencies:

Jetpack Compose: A modern toolkit for building native Android UI.
Retrofit: A type-safe HTTP client for Android.
Gson: A library for converting Java objects to JSON and vice versa.
Room: Provides an abstraction layer over SQLite to handle local data storage.

Make sure you have the appropriate dependencies configured in your project before running the app.

## API Usage
The Star Wars Search App uses the swapi.dev API as its data source. The API provides information about characters, starships, planets, and more from the Star Wars universe. To learn more about the API and explore its endpoints, visit swapi.dev.

## Database
The Star Wars Search App implements a Room Database to store your favorite characters and starships. The database allows you to add, retrieve, and delete your favorite items in a persistent manner.
