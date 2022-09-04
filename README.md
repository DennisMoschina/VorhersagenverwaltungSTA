# Vorhersagenverwaltung mit der SensorThings API

This project creates the backend for a GUI to better navigate the SensorThingsAPI.

## Run it using docker

You can run only this server using docker with the following steps or use it in combination with the
[frontend](https://gitlab-ext.iosb.fraunhofer.de/ilt-pse/ss22-vorhersagenverwaltung-mit-der-sensorthings-api/vorhersagenverwaltungsta_frontend)
using docker-compose
<p>
In order to run the application, you need to follow these steps:

1. get the image of the code in this repository from the container-registry in this repository
2. create a file with a list of the desired catalogues with the following structure:
    ```json
   [
      {
        "id": 7,
        "url": "catalogue.url.org",
        "name": "CatalogueName",
        "description": "CatalogueDescription"
      }
   ]
   ```
3. set the `CATALOGUE_LIST` environment variable to the location of the previously created file
4. create a file with a list of the password protected servers and its authentications:
    ```json
   [
      {
        "url": "server.url.org",
        "auth": {
          "user": "username",
          "password": "user password"
        }
      }
   ]
   ```
5. set the `FROST_AUTH` environment variable to the location of the previously created file
6. expose port `80/TCP`

## Background

This application emerged from a practical work for a lecture at [KIT](http://www.kit.edu/) in collaboration with the [Fraunhofer IOSB](http://iosb.fraunhofer.de/).
A [server implementation](https://github.com/FraunhoferIOSB/FROST-Server) of the SensorThingsAPI, developed by the Fraunhofer IOSB, is available on GitHub as well.