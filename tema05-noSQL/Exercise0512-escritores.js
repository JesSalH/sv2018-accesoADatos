// Escritores

use escritores;

// 1. Añade Alejandro Dumas, nacido en 1802, con obras 
// "Los Tres Mosqueteros" y "El Conde de Montecristo".

db.escritores.save(
{
	nombre: "Alejandro Dumas",
	nacido: 1802,
	libros: [
		{nombre: "Los Tres Mosqueteros"},
		{nombre: "El Conde de Montecristo"}
	]
}
);


// 2. Añade (a la vez):  Homero, cuya fecha exacta de nacimiento
// no es conocida, con libros "Ilíada" y "Odisea", y Mary Shelley, 
// autora de "Frankenstein".

db.escritores.save(
[
{
	nombre: "Homero",
	libros: [
		{nombre: "Ilíada"},
		{nombre: "Odisea"}
	]
},{
	nombre: "Mary Shelley",
	libros: [
		{nombre: "Frankenstein"}
	]
}
]);


// 3. Modifica los datos: Mary Shelley nació en 1797.

db.escritores.update(
{ nombre: "Mary Shelley"  },
{ $set :  { nacido: 1797, } }
);


// 4. Añade "Veinte años después" a las obras de Alejandro Dumas.

db.escritores.update(
{ nombre: "Alejandro Dumas" },
{ $addToSet : { libros: {nombre: "Veinte años después"} } }
);


// 5. Muestra los libros (y demás datos) de Homero.

db.escritores.find({nombre:"Homero"});



// 6. Muestra los libros de autores nacidos antes de 1800.

db.escritores.find({nacido:{$lt:1800}});


// 7. Muestra los libros de autores nacidos entre 1750 y 1850.

db.escritores.find({$where: "this.nacido >= 1750 & this.nacido <= 1850"});

db.escritores.find({$and: [
	{nacido:{$gte:1750}}, {nacido:{$lte:1850}}
]});

db.escritores.find({nacido:{$gte:1750, $lte:1850}});  // "and" implícito


// 8. Muestra el nombre de cada autor junto a la cantidad de libros
// suyos de los que tenemos constancia.

db.escritores.aggregate([
   {
      $project: {
         nombre: 1,
         cantidadDeLibros: { 
			 $size: "$libros" }
      }
   }
] );


// 9. Añade como índice el nombre del autor.

db.escritores.createIndex( { nombre: 1 } );


// 10. Hemos perdido el libro de Mary Shelley. Borra su información
// de nuestra base de datos.

db.escritores.deleteOne({nombre:"Mary Shelley"});
