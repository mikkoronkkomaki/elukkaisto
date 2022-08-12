# Elukkaisto

Small example project for showing how to use PostgreSQL databases with (Clojure) unit tests. 

The main idea is to split the database in three:
1. App DB which is used with the application itself
2. Template DB which is created from the intial App DB and used as a baseline for the Test DB
3. Test DB which is a copy of the Template DB. The copy is made everytime the test fixture is set up.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running


## License

Copyright © 2022 FIXME
