<?php
$host ="localhost";
$usuario = "root";
$password = "root";
$baseDeDatos = "adatos";
$mysqli = new mysqli($host,
    $usuario, $password, $baseDeDatos);

if ($mysqli->connect_errno) 
{
    echo "0|0\n";
    exit();
}

$mysqli->query("SET NAMES utf8;");

// Ejemplo más real, que recibe información por GET
// para saber los datos que debe procesar posteriormente

$orden = "SELECT * FROM alumnos 
WHERE anyoNacimiento = ". $_GET["anyo"] ."
ORDER BY nombre";

if ($resultado = $mysqli->query($orden))
{
    while ($registro = $resultado->fetch_array())
    {
        echo $registro["nombre"] . "|" .
            $registro["anyoNacimiento"] . "\n";
    }
}
else
{
    echo "0|0\n";
    exit();
}
    
$resultado->close();
