# simple-database
A small Java object-relational mapping for quick and dirty database connectivity.

## Installation
This stuff is still being worked out. For now, note that your project will require the SQLite mini DBMS. That library is not stored on this repo; download it separately from [here](https://bitbucket.org/xerial/sqlite-jdbc/downloads).

## tl;dr
1. Import the library into your project.
2. To create a table, subclass *DataObject*. Any public fields in your subclass that are 
subclasses of *Column* will be turned into the columns of your table.
3. Remember to include an initialisation routine that calls *SimpleDB.CreateTable* somewhere in your code!
It belongs in your setup or installation codes.
4. To CRUD rows in the database, use the factory methods within *SimpleDB*. Do **not** instantiate a child of *DataObject*
using the new keyword because it will fail.

## More to come ..
