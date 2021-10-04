# README
To build use:
`mvn package`

To run use:
`./run`

To start the server use:
`./run --gui [--port=<port>]`

To use this code, simply run the main method to begin the REPL.

When the user types "users online", a new Aggregator is
Instantiated. From there, the "loadData" method is called, which
is the main method that runs the aggregator. This method calls the
"ping" method, which pings an endpoint a specified k number of 
times. After each ping, the json retrieved from the endpoint is
parsed and identified. If it's the correct type of data, it is
merged with our current database. If not, it is ignored. A hashmap
from user id's -> users is maintained in order to quickly and
accurately determine if a user is already in the database. The
JSONopener parses the json string returned from the endpoint as
a jsonArray using .fromJson(), which keeps the data type general. 
Although functionality for retrieving review/rent data does not 
seem necessary for this project, this design would allow for that
functionality later on. The JSONopener also identifies the data
as user, review, rent, or error (which doesn't return anything).

When the user types just "users", then the JSONopener is called
to open a local json file. To parse the file, a new reader is
instantiated and then passed into .fromJson(), which automatically
stores the objects in an array. From there, the JsonObjects are
converted into User objects and stored in a hashmap. The local
userArray and userHashmap fields of JSONopener were created to
allow the program to access these datastructures without
instantiating an Aggregator (which deals with the data obtained
online).
