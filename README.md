![Kotlin](https://img.shields.io/badge/kotlin-7620A9?style=for-the-badge&logo=Kotlin&logoColor=ffdd54)
![Jetpack Compose](https://img.shields.io/badge/jetpackcompose-999HI4?style=for-the-badge&logo=Jetpack%20compose&logoColor=ffdd54)
![DaggerHilts](https://img.shields.io/badge/daggerhilt-%23E34F26.svg?style=for-the-badge&logo=Kotlin&logoColor=white)
![RoomDatabase](https://img.shields.io/badge/roomdb-%245672B6.svg?style=for-the-badge&logo=sqlite&logoColor=white)

# Movie_List
Retriving List of Movies from Movie Database API Implemented with Kotlin, Jetpack Compose, Paging, RoomDatabase and DiggerHilts

## Description
- This application was built to satisfy certain requirements, and it simply involve retriving list of movies from movie database api. 
- This task was carried out using kotlin and jetpack and other jetpack packages.

## How it works
- The application needs to load the data from the server through the internet to serve user.
- Data on been viewed, gets saved to Roomdatabase, and this then enable the user to access these list without internet another time.
- Loading of the list is be controlled by using pagingthe pagging loads the data bit by bit to save user loading uncessary contents. 
- Application state is been handled by the viewModel

## Tech Stack

**Client:** 
`Kotlin` as `Backend language`, 
`JetPack Compose` as `frontend Language`,
`RoomDatabase` as `local database`,
`Ktor` to `Handle HttpRequest`
`DaggerHilt` as `Constructor Injector`
`Pagging` to `Handle List Loading`,
`ViewModel` to `Handle State`

## Features

`Access List Online` Enables users to get new list of movies to watch or recommend.

`Access List Offline` Enables users to get view loaded list without internet.

## End Point

`GET` `https://api.themoviedb.org/3/movie/{movie_id}/lists?api_key=<<api_key>>&language=en-US&page=1`.






