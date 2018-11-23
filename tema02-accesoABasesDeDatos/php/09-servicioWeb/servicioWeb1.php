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

// Ejemplo de cómo mostrar todos los datos en un formato sencillo
// de procesar posteriormente

$orden = "SELECT * FROM alumnos ORDER BY nombre";
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
