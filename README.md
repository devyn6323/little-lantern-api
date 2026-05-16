# Little Lantern API

Spring Boot backend for the Little Lantern story app.

## Features

- Generate toddler-safe stories
- Save stories to PostgreSQL
- Load saved stories
- Delete saved stories

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL

## API Endpoints

POST /api/stories/generate

GET /api/stories/saved

POST /api/stories/saved

DELETE /api/stories/saved/{id}
