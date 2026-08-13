# AsteroidsFX

Asteroids written in Java 21 and JavaFX. The game is split into modules that are
loaded at runtime from the `plugins/` folder, so a component can be removed
without recompiling anything.

## Build and run

```
./build.sh
./run.sh
```

`build.sh` builds every module and copies the jars into `plugins/`.
`run.sh spring` starts the same game, but assembled by the Spring container.

## Controls

| Key | Action |
| --- | --- |
| Left / Right | turn |
| Up | thrust |
| Down | reverse |
| Space | shoot |

## Score service

```
cd ScoringSystem
mvn spring-boot:run
```

Runs on port 8080 with `GET /score`, `POST /score/add?points=10` and
`POST /score/reset`. The game shows the score in the top of the window, and
keeps running normally if the service is not started.

## Modules

- `Common` - entities, game data and the service interfaces
- `CommonBullet`, `CommonAsteroids` - shared entities and interfaces
- `Player`, `Enemy`, `Asteroids`, `Bullet`, `Collision` - the game components
- `Score` - talks to the score service over HTTP
- `Core` - the window and the game loop, loads the components
- `ScoringSystem` - the Spring Boot score service

## Tests

```
mvn test
```

## Branches

One branch per lab exercise: `gamelab`, `javalab`, `jpmslab1`, `jpmslab2`,
`jpmslab3`, `springlab`, `testlab`, `microservicelab`. `main` is the full
project.
