// Crea una base de datos "examenes", con una colección "examenes" (nombre, fecha, nota):

use examenes

// Añade un dato de un examen: SGE, Enero 2019, 7.9

db.examenes.save({nombre:"SGE","fecha":"Enero 2019",'nota':7.9})

// Añade dos datos más: AD, Febrero 2019 (sin nota); Móviles, Febrero 2019, 8.4

db.examenes.save(
    [
        {nombre: "AD",
            fecha: "Febrero 2019"}
        ,
        {nombre: "Móviles",
            fecha: "Febrero 2019",
            nota: 8.4}
    ]
)

// Finalmente, muestra todos los datos.

db.examenes.find()
