// Alumnos

use alumnos;

db.alumnos.save(
{
    nombre: "Nestor",
    nacido: 1990,
    empresaActual: "Microsoft",
    cargo: "CTO Windows",
    ultimoContacto: ISODate("2019-01-17"),
    empresasAnteriores:[
        {nombre: "Facebook"},
        {nombre: "Unity"}
    ]
}
);

