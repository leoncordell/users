Project Description
Using the language of your choice please build your own API which calls the API at https://bpdts-test-app.herokuapp.com/, and returns people who are 
listed as either living in London, or whose current coordinates are within 50 miles of London.

Swagger :
http://localhost:8080/base/swagger-ui.html
http://localhost:8080/base/v2/api-docs

Url endpoints (Use. eg postman) :
localhost:8080/base/city/London/users/all        
and 
localhost:8080/base/city/London/users  (London only)
localhost:8080/base/city/London/users/catchment  (50 mile radius only )


herokuapp endpoint's Used :
a.bpdts-test-app.herokuapp.com/city url  -  
b.https://bpdts-test-app.herokuapp.com/users


Assumptions:
1. The results for LONDON using the City api are assumed correct ,whatever 
the persons coordinates . 
2. For other users the distance from London is based on longitude/latitude coordinates of the person compared , 
to the london coordinates (London's coordinates are taken from charing cross railway station). 
 