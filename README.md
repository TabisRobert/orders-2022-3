# orders-2022-3

## Java

OpenJDK 17

## Maven

Maven 3.8.4

If we want to publish our artifacts we should use properly:  
https://www.mojohaus.org/flatten-maven-plugin/  
To create poms without **${revision}**

## Alternative Gradle

mvnw.bat

## Project structure

Modules:  
- api: data structures, interface facade
- application: domain logic implementation
- infrastructure: implementation details (around technologies)

## Orders functions

- add product
- adding product multiple times results increasing quantity
- remove product
- search for order
- create order

### Data structure

- id
- items
- amount

### Item structure

- product
- quantity

## Products functions

- define product
- search for product

### Data structure

- id
- name
- price
