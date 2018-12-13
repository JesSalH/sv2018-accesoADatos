//Jairo Pérez Sánchez

// Acercamiento a M:M (2)

// Base de datos para guardar información de "cursos".

// Tenemos los cursos de Express (ex), NodeJS (nj) y Unity (un). 
// Los asistentes son Vilaplana (3, curso "un"), Saorín (4, curso "nj"), 
// Bohez (555, en los 3 cursos).

use cursos

db.cursos.save(
    {id:"nj",
    nombre:"NodeJS",
    asistentes:[
        {dni:4,nombre:"Saorín"}
        ,{dni:555,nombre:"Bohez"}]
    }
);

db.cursos.save(
    {id:"un",
    nombre:"Unity",
    asistentes:[
        {dni:5,nombre:"Vilaplana"},
        {dni:555,nombre:"Bohez"}]
    }
);

db.cursos.save(
    {id:"ex",
    nombre:"Express",
    asistentes:[
        {dni:555,nombre:"Bohez"}]
    }
);

db.cursos.find();
