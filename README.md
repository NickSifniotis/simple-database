# simple-database
A small Java object-relational mapping for quick and dirty database connectivity.

## Requirements
- Java JDK 1.8 or better. This code uses streams, lambda expressions and other lazy programming techniques that
previous versions of Java do not support.
- SQLite, a compact standalone RDBMS that you can import directly into your Java projects. It is available 
[here](https://bitbucket.org/xerial/sqlite-jdbc/downloads).

## Installation
TBA. You will probably include the JAR file in your project the way that you would any other library. 

## tl;dr
1. Import the library into your project.
2. To create a table, subclass *DataObject*. Any public fields in your subclass that are 
subclasses of *Column* will be turned into the columns of your table.
3. Remember to include an initialisation routine that calls *SimpleDB.CreateTable* somewhere in your code!
It belongs in your setup or installation codes.
4. To CRUD rows in the database, use the factory methods within *SimpleDB*. Do **not** instantiate a child of *DataObject*
using the new keyword because it will fail.

## More to come ..
