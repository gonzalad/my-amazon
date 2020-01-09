# Spring Boot and Angular App

For now, this is a simple startup app with:

* Spring Boot based backend
* Angular based front end

## Build

Maven will build both frontend and backend

```
mvn clean package
```

If you want to build the docker images too, just execute:

```
mvn clean package docker:build
```

## Run

Either directly from the IDE or command line:

* backend: start main class MyAmazonApplication from Intellij
* frontend: execute `npm run start`

Or start the docker images:

```
# backend
docker run --rm -p 8080:8080 my-amazon-backend:latest
# frontend
docker run --rm -p 80:80 my-amazon-frontend:latest
```


## Front end instructions

### Project setup

#### Bootstrap installation

see https://awesome-angular.com/2019/03/10/bootstrap-angular-cli/

#### Main Module

This module will represent the layout of the web site

```
ng g module modules/main --module=app --routing=true 
ng g component modules/main/components/main --module=main 
```
And then :

* add MainComponent in the bootstrap attribute of MainModule 
* copy app.component.html content to main.component.html
* replace app.component.html with `<router-outlet></router-outlet>`

### Share Module

The module will contain the shared content that will be used by all other modules (i.e. dialog components, ...)

```
ng g module modules/share
```


### Functional Module

Then creating a new functional module is just:

* create module
```
ng g module modules/<name> --routing=true
```
* in the new module: add ShareModule in the imports attribute
i.e.
```
ng g module modules/product --routing=true
ng g component modules/product/components/product-list --module=product --flat=true 
```
* add a routerLink to this module, ie in main.component.html, I added
```
<a class="p-2 text-dark" [routerLink]="['/products']">Products</a>
```