

Application returns all users who reside within a 50 mile (Configurable) radius  
from a specific city (Default London) .

e.g 
User A in Northampton wilpl be outside the 50 radius from London
User B in Bedford would be within the 50 miles radius from London

Swagger :
http://localhost:8080/base/swagger-ui.html
http://localhost:8080/base/v2/api-docs

Postman endpoints :
localhost:8080/base/city/London/users

localhost:8080/base/city/London/users/all


API endpoint's Used :
a.bpdts-test-app.herokuapp.com/city url  -  

b.https://bpdts-test-app.herokuapp.com/users


Assumptions:
1. The results for LONDON using the City api are assumed correct ,whatever 
the coordinates . 
2. For other users the distance from London is based on longitude/latitude coordinates, 
and the london coordinates are taken from charing cross railway station. 
 