// Pedro Luis Coloma

use baterias;

// 1

db.baterias.save(
{
	marca: "PowerAdd",
	modelo: "MP-3418BK",
	capacidad: 10000,
	precio: 13.99,
	peso: 281,
	usb: 3
}
);

// 2

db.baterias.save(
[
{
	marca: "Aukey",
	modelo: "PB-N35",
	capacidad: 20000,
	precio: 26.99,
	peso: 449,
	usb: 2
},{
	marca: "PowerAdd",
	modelo: "Slim2",
	capacidad: 5000,
	precio: 10.99,
	peso: 195,
	usb: 1
}
]);

// 3

db.baterias.update(
{
	marca: "PowerAdd",
	modelo: "Slim2" 
},
{
	$set : 
	{
		colores :
		[
			{color: "negro"},
			{color: "azul"},
			{color: "rosa"}
		]
	}
}
);

db.baterias.update(
{
	modelo: "MP-3418BK" 
},
{
	$set : 
	{
		colores :
		[
			{color: "negro"}
		]
	}
}
);

db.baterias.update(
{
	modelo: "PB-N35" 
},
{
	$set : 
	{
		colores :
		[
			{color: "negro"}
		]
	}
}
);

// 4

db.baterias.find({marca:"PowerAdd"});

// 5

db.baterias.find({capacidad:{$gt:6000}});

// 6

db.baterias.find({colores:{color:"negro"}});

// 7

db.baterias.find({$where: "this.capacidad/this.precio > 500"});

// 8

db.baterias.aggregate([
   {
      $project: {
         modelo: 1,
         cantidadDeColores: { 
			 $size: "$colores" }
      }
   }
] );

// 9

db.baterias.createIndex( { modelo: 1 } );

// 10

db.baterias.deleteOne({marca:"Aukey", modelo:"PB-N35"});
