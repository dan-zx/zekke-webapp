ZeKKe WebApp
=================

<img src="https://dl.dropboxusercontent.com/u/1995295/img/ZeKKe/launcher-web.png" height="100px" align="left" />

ZeKKe is an orientation system for access in private spaces where no roads exist in systems like Google Maps or Bing Maps. The main goal of ZeKKe is to provide shortest routes between two places of interest in a private space and also give additional about these places by developing web applications and smart phone apps.

ZeKKe Project is based on my bachelor's thesis at Universidad de las Américas Puebla (http://bit.ly/1dIZyx0) and a paper I submitted at CONIELECOMP 2011 (http://bit.ly/15d8e7b).

## API Endpoints

#### Geocoder

* Retrieves the place located at the given geographic point

  ```
  https://zekke.herokuapp.com/api/v1/geocoder/places/by-position/place.json
  ```

  HTTP Method: **GET**

  | Parameters | Type  | Example | Decription                        |
  | ---------- |------ | ------- | --------------------------------- |
  | latitude   | Query | 44.3    | Latitude of the place's location  |
  | longitude  | Query | 37.2    | Longitude of the place's location |

* Retrieves the list of places with a name like the given name

  ```
  https://zekke.herokuapp.com/api/v1/geocoder/places/like/{name}.json
  ```

  HTTP Method: **GET**

  | Parameters | Type | Example  | Decription                    |
  | ---------- | ---- | -------- | ----------------------------- |
  | name       | Path | "A name" | Part of the name place's name |

* Retrieves the list of places' names that are in the radius of the given geographic point with the name like the given name

  ```
  https://zekke.herokuapp.com/api/v1/geocoder/names/in-area/like/{name}.json
  ```

  HTTP Method: **GET**

  | Parameters | Type  | Example  | Decription                         |
  | ---------- | ----- | -------- | ---------------------------------- |
  | name       | Path  | "A name" | Part of the name place's name      |
  | latitude   | Query | 44.3     | Latitude of the place's location   |
  | longitude  | Query | 37.2     | Longitude of the place's location  |
  | radius     | Query | 200.23   | Radius to search within, in meters |

#### Directions

* Provides an optimal route between the given positions if exists

  ```
  https://zekke.herokuapp.com/api/v1/route-finder/route.json
  ```

  HTTP Method: **GET**

  | Parameters       | Type  | Example | Decription                               |
  | ---------------- | ----- | ------- | ---------------------------------------- |
  | root-latitude    | Query | 44.3    | Latitude of the root place's location    |
  | root-longitude   | Query | 37.2    | Longitude of root the place's location   |
  | target-latitude  | Query | 44.3    | Latitude of the target place's location  |
  | target-longitude | Query | 37.2    | Longitude of the target place's location |
