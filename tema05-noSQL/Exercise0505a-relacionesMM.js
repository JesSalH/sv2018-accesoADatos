//Jairo P�rez S�nchez

// Acercamiento a M:M (1)

// Cursos de NodeJS (nj),
// con dos asistentes: Saor�n (4) y Bohez (555)

use cursos

db.cursos.save(
    {
        id:"nj",
        nombre:"NodeJS",
        asistentes:[
            {dni:4,nombre:"Saor�n"},
            {dni:555,nombre:"Bohez"}]
    }
);
