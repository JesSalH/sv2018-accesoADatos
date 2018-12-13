/* JORGE CALABUIG BARTUAL */

// En la base de datos "examenes":

// Crea una nueva colección llamada "profesores". En ella introducirás un primer 
// profesor, con nombre "Nacho" y código "N".

use examenes;
db.profesores.save({nombre:"Nacho", codigo:"N"});


// Introduce un segundo profesor, con nombre "Cristian", código "C" y que ha 
// ganado 8 concursos.

db.profesores.save({nombre:"Cristian", codigo:"C", concursos:8});


// Muestra todos los profesores

db.profesores.find();


// Muestra los datos del profesor de código N

db.profesores.find({codigo:"N"});


// Modifica el profesor "Nacho", para añadir que ha ganado 2 concursos. Usa la 
// sintaxis de $set.

db.profesores.update(
    {codigo:"N"},
    {$set:{concursos:2}});

// Busca todos los profesores que hayan ganado 5 concursos o más.

db.profesores.find({concursos: {$gte:5}});
