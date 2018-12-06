/* Ejercicio 05.01: Contacto con MongoDB */
/* Jorge Calabuig Bartual */

// Ver bases de datos existentes
show dbs

// Ver base de datos en uso
db

// Comenzar a usar una nueva base de datos
use everis2

// Guardar un dato (sólo con nombre) en la colección "juegos"
db.juegos.save({nombre:"Fournight"});

// Guardar un dato (con nombre y plataformas) en la colección "juegos"
db.juegos.save({"nombre":"Los Seems", "plataforma":"Sega Saturn"});

// Ver los datos de la colección "juegos"
db.juegos.find();

// Guardar dos dato (como array) en la colección "juegos"
db.juegos.save(
[
    {"nombre":"The Crow","plataforma":"Android"}, 
    {"nombre":"Assasins's Cry"}
]);
