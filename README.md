# BookingSpringKGLSol
 
 Bardziej czytelny ReadMe : https://raw.githubusercontent.com/Qwopfear/BookingSpringKGLSol/main/README.md
 
 
 
 curl for this app: 
Post : 
  curl --data "name=name" http://namelocalhost:8080/api/clients/add?
  curl --data "name=name&description=desc&price=100&area=10" http://localhost:8080/api/rent/add?
  curl --data "client_id=1&rent_id=1c&rentStart=2022-08-31&rentEnd=2022-09-22" http://localhost:8080/api/reservation/add
Get :   
  Client:  
     All: curl http://localhost:8080/api/clients/all
     ById: curl http://localhost:8080/api/clients/{id}
RentableObj: 
     All: curl http://localhost:8080/api/rent/all
     ById: curl http://localhost:8080/api/rent/{id}
Reservation:
    All: curl http://localhost:8080/api/reservation/all
     ById: curl http://localhost:8080/api/reservation/{id}

Delete client/rentObj/reservation by id:
  curl -X "DELETE"  http://localhost:8080/api/clients/{id}
  curl -X "DELETE" http://localhost:8080/api/rent/{id}
  curl -X "DELETE" http://localhost:8080/api/reservation/{id}

Update : 
  curl -X "PATCH" -d "name=name"  http://localhost:8080/api/clients/{id}/update
  curl -X "PATCH" -d "name=name&description=desc&price=100&area=10" http://localhost:8080/api/rent/{id}/update
  
Uzylem 
  mvn package 
 jako skrypt kompilujący

Dla starta aplikacji

java -jar path/target KGLSol-0.0.1-SNAPSHOT.jar 
