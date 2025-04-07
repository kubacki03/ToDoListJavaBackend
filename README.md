ToDoListJavaBackend - backend do kolejnego nudnego projektu todolist like.
Jest to prosty backend stworzony w Javie na frameworku Spring Web, jako swoją baze danych wykorzystałem MySQL, do komunikacji z bd wykorzystuje Hibernate i JPA.
Do autoryzacji/autentykacji wykorzystałem SPRING SECURITY oraz JWT.

##  Powiązany projekt
Frontend do tej aplikacji powstał w Angular i jest dostępny tutaj: https://github.com/kubacki03/ToDoList.git

## Funkcje
-Logowanie/Rejestracja
-Tworzenie nowych zadan
-Usuwanie zadan
-Sortowanie zadan

## Technologie
-JAVA
-SPRING WEB
-SPRING SECURITY
-JPA
-Hibernate
-Maven
-Lombok

## Instalacja
1) Sklonuj repozytorium
   git clone https://github.com/kubacki03/ToDoListJavaBackend.git
2) Przejdz do folderu projektu
   cd ToDoListJavaBackend
3)Uzupelnij plik application.properties swoimi danymi bazy danych mysql
4)Skompiluj projekt
  ./mvnw clean install
5)Uruchom aplikacje
  ./mvnw spring-boot:run

