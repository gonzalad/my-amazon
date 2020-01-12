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
* add a route in parent module, i.e. I added this to main-routing.module.ts (I use lazy loaded modules here)
```
children: [
  {
    path: 'products',
    loadChildren: () => import('../product/product.module').then(mod => mod.ProductModule)
  }
]
```
* add a routerLink to the previous route, ie in main.component.html, I added
```
<a class="p-2 text-dark" [routerLink]="['/products']">Products</a>
```
* in product-routing.module.ts, define a main component when we arrive at /product:
```
const routes: Routes = [
  {
    path: '',
    component: ProductListComponent,
  }
];
```

##Neo4j Primer

start neo4j with `neo4j console`
neo4j browser is available at http://localhost:7474/browser/

Sample queries:
* MATCH (p:product) RETURN p
* (p:Person {name: "Jennifer"})-[rel:LIKES]->(g:Technology {type: "Graphs"})
* CREATE (p:Product {name: 'The Lord of the rings'})
  RETURN p
see https://neo4j.com/developer/cypher-basics-i/
see https://neo4j.com/developer/guide-build-a-recommendation-engine/
see https://medium.com/neo4j/neo4j-ogm-and-spring-data-neo4j-a55a866df68c about ids

Neo4j OGM
https://neo4j.com/docs/ogm-manual/current/tutorial/#tutorial:introduction

Database migrations
http://www.liquigraph.org/
see also https://stackoverflow.com/a/40610291
see also https://github.com/liquigraph/liquigraph/tree/master/liquigraph-examples/spring-boot