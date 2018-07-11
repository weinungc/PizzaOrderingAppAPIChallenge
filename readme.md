
# Pizza Ordering App

It's an example for Pizza ordering app

## Getting Started

This video shows how its work
https://youtu.be/jWG72eYOwPk

### Prerequisites

You need to install following:

 - [MongoDB](https://www.mongodb.com/download-center)
 - [Java](https://java.com/download/)


## Running the tests

### Running the data

To start MongoDB, use follow command
```
mongod
```

and to stop

```
mongod --shutdown
```

### Running Application

To start the project, you can run the command in project folder

```
./mvnw spring-boot:run
```

you will nee to [add ingredients](http://localhost:8080/webingredient) before [post an order](http://localhost:8080/)

 - OrderPage: http://localhost:8080/
 - OrderDetail:http://localhost:8080/weborder/{id}
 - OrderDetail-JSON:http://localhost:8080/order/{id}
 - OrderDetailAll-JSON:http://localhost:8080/order/all
 
 - AddIngredient:http://localhost:8080/webingrient
 - Ingredient-JSON:http://localhost:8080/ingrient/{id}
 - IngredientAll-JSON:http://localhost:8080/ingrient/{id}
 - IngredientName-JSON:http://localhost:8080/ingredient?name=ingredient_name


## RESTFUL API Support

 

 - Ingredient
	 - PUT "ingredient"
	 - POST "ingredient"
	 - GET "ingredient?name=ingrident_name"
	 - Get "ingredient/all"
	 - Get "ingredient/{id}"
	 - DELETE "ingredient/{id}"
	 - PUT "ingredient/{id}" 
 - Order
	 - POST "order"
	 - Get "order/all"
	 - Get "order/{id}"
	 - DELETE "order/{id}"
	 - PUT "order/{id}" 


## Built With

* [Spring](https://spring.io//) - Java framework
* [MongoDB](https://www.mongodb.com/download-center) - The database used
* [AbgularJS](https://angular.io/) - Font-end 
* [maven](https://maven.apache.org/) - Project management tool
* [bootstrap](https://getbootstrap.com/) - Project management tool



## Authors

* **Wei-Nung Chao** - *Initial work* - [blog](https://weinungc.github.io/)
