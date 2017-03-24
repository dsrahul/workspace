[![Build Status](https://travis-ci.org/dsrahul/workspace.svg?branch=master)](https://travis-ci.org/dsrahul/workspace)
# Workspace Code stack

This is a collection of multiple microservices

* **Marketplace**
    *  An independent spring-boot project I created as part of a CC for an interview. Further documentation is available within the project folder. This uses a in memory H2 database.
* **Employee**
    * An independent spring-boot application again but this uses MONGODB. This was created to try docker. More details in the docker section.
* **Missions**
* **Rewards**
    * Same as employee created to see if this can speak to the same instance of MONGODB

Each of these are self contained/independent spring-boot maven projects.

## Continuous Integration

The code is connected to the GITHUB repository. Which in turn has webhooks into Travis CI.
A .travis.yml and a pom.xml file have been included to enable Travis to build the projects as soon as code is checked into the repository. I am still trying to figure out the best mechanism to build the projects independently.

## Docker
A Dockerfile has been included in each of the projects to create seperate docker containers. A docker composer file has been included to group all the modules together. Contents of the file are

```
mongodb:
 image: mongo
 ports:
   - "39000:27017"
 volumes:
  - /d/docker/images/mongodb/data:/data/db
  - /d/docker/images/mongodb/log:/var/log/mongodb
employee:
 build: dkr-employee-mongodb
 ports:
  - "8080"
 links:
  - mongodb
reward:
 build: dkr-rewards-mongodb
 ports:
  - "8080"
 links:
  - mongodb
mission:
 build: dkr-missions-mongodb
 ports:
  - "8080"
 links:
  - mongodb
ha_employee:
 image: tutum/haproxy
 links:
   - employee
 ports:
   - "8080:80"
```

We will look at each of these in greater detail.
1. **mongodb**
    * This service is derived from a base image of _mongo_. 
    * The docker machine port of 27017 is exposed to the Host machine port of 39000. This ensures a static availablility of mongodb url. The docker machine ip **192.168.99.100** is mapped to **localdocker.me**. 
    * host volumes are attached to the container volumes. This allows for persistence beyond the container life.
    * The mongodb url is
```
    mongodb://localdocker.me:39000/
```
    
2. **employee**
3. **rewards**
4. **missions**

### Usage
* Open up the docker terminal and cd to this directory.
* At the prompt type
```
docker-compose build --no-cache
```