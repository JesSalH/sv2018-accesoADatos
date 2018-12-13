//Jairo Pérez Sánchez

// Acercamiento a M:M (1)

// Cursos de NodeJS (nj),
// con dos asistentes: Saorín (4) y Bohez (555)

use cursos

db.cursos.save(
    {
        id:"nj",
        nombre:"NodeJS",
        asistentes:[
            {dni:4,nombre:"Saorín"},
            {dni:555,nombre:"Bohez"}]
    }
);
