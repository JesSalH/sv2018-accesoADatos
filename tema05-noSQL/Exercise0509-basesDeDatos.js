// Bases de datos

use basesDeDatos;

// 1. Añade:
// MySQL, Relacional, Open source, para: Linux, Windows, MacOS X
db.basesDeDatos.save(
{
	nombre: "MySQL",
	tipo: "Relacional",
	licencia: "Open source",
	plataformas: 
	[
		{nombre: "Linux"},
		{nombre: "Windows"},
		{nombre: "MacOS X"}
	]
}
);

// 2. Añade (a la vez): 
// SQL Server: Relacional, Propietario, para: Windows
// Cassandra: NoSQL, Open source, para: Linux
db.basesDeDatos.save(
[
{
	nombre:"SQL Server",
	tipo:"Relacional",
	licencia:"Propietario",
	plataformas:
	[
		{nombre:"Windows"}
	]
},{
	nombre:"Cassandra",
	tipo:"NoSQL",
	licencia:"Open source",
	plataformas:
	[
		{nombre:"Linux"}
	]
}
]);

// 3. Muestra todos los gestores de base de datos que tenemos anotados.

db.basesDeDatos.find();

// 4. Muestra los relacionales.

db.basesDeDatos.find({tipo:"Relacional"});

// 5. Muestra los que contienen "SQL" en su nombre.

db.basesDeDatos.find({nombre:{$regex:/SQL/}});

// 6. Muestra los que existen para Linux

db.basesDeDatos.find({plataforma:{nombre:"Linux"}});

// 7. Añade PostgreSQL, Relacional, Propietario

db.basesDeDatos.save(
{
	nombre: "PostgreSQL",
	tipo: "Relacional",
	licencia: "Propietario"
}
);

// 8. Edita PostgreSQL, para que sea Relacional y Open source, indicando todos los valores de todos los campos.

db.basesDeDatos.update(
{
	nombre: "PostgreSQL" 
},
{
	nombre: "PostgreSQL",
	tipo: "Relacional",
	licencia: "Open source"
}
);

// 9. Edita PostgreSQL, para añadir que está disponible para Linux, Windows, MacOS X, sin indicar todos los valores de todos los campos.

db.basesDeDatos.update(
{
	nombre: "PostgreSQL" 
},
{
	$set : 
	{
		plataformas: 
		[
			{nombre: "Linux"},
			{nombre: "Windows"},
			{nombre: "MacOS X"}
		]
	}
}
);

// 10. Añade el detalle de que SQL Server también existe para Linux.
db.basesDeDatos.update(
{
	nombre: "SQL Server" 
},
{
	$addToSet : 
	{ plataformas: 
			{nombre: "Linux"}
	}
}
);

// 11. Muestra el nombre de cada gestor de bases de datos, junto con la cantidad de plataformas para las que está disponible.

db.basesDeDatos.aggregate([
   {
      $project: {
         nombre: 1,
         cantidadDePlataformas: { 
			 $size: "$plataformas" }
      }
   }
] );

// 12. Queremos que el nombre de cada gestor esté indexado.

db.basesDeDatos.createIndex( { nombre: 1 } );

db.basesDeDatos.getIndexes();
