// ----------------------------
// Búsquedas y modificaciones
// ----------------------------

// Buscar un cierto nombre

db.examenes.find(
	{nombre: "AD"}
)

// las mayúsculas deben ser exactas: esto no muestra nada

db.examenes.find({nombre: "ad"})

// Modificación: se indica criterio de búsqueda y nuevos valores

db.examenes.update(
	{nombre: "AD"},
	{nombre: "AD", fecha: "Febrero 2019", nota: 9.1}
)

// Si omitimos algún dato, se perderá (fecha en este caso):

db.examenes.update(
	{nombre: "AD"},
	{nombre: "AD", nota: 9.1}
)

// Alternativa para cambiar sólo un campo: $set

db.examenes.update(
	{nombre: "AD"},
	{
		$set : {fecha: "Febrero 2019"}
	}
)

// Para buscar con operadores de comparación:

db.examenes.find(
	{nota: {$gt : 8.0}}
)

