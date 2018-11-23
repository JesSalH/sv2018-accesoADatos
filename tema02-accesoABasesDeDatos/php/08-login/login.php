<!doctype html>
<html>
    <head>
        <title>Login</title>
        <meta charset="utf-8" />
    </head>
    
    <body>
<?php
if (! isset($_POST["nombre"])) 
{
?>  
    
        <h1>Login</h1>
        <form method="post"> 
            <input type="text" name="nombre" placeholder="Nombre" />
            <br />
            <input type="text" name="anyo" placeholder="Password" />
            <br />
            <input type="submit" value="Buscar" />
        </form>
<?php
}
else
{
    $host ="localhost";
    $usuario = "root";
    $password = "root";
    $baseDeDatos = "adatos";
    $mysqli = new mysqli($host,
        $usuario, $password, $baseDeDatos);
    
    if ($mysqli->connect_errno) 
    {
        echo "Ha fallado la conexión: ". $mysqli->connect_error;
        echo "</p></body></html>";
        exit();
    }
    
    $nombre = $_POST["nombre"];
    // Esta es una (de varias) forma sencilla de reducir riesgos
    // de ataques de inyección de SQL
    $password = addslashes($_POST["anyo"]);
    
    $mysqli->query("SET NAMES utf8;");

    $busqueda = $_POST["nombre"];
    $orden = "SELECT * FROM alumnos
        WHERE nombre ='". $nombre ."'
        AND anyoNacimiento ='". $password ."'";
    
    echo $orden;
    if ($resultado = $mysqli->query($orden))
    {
        if ($resultado->num_rows > 0)
            echo "Welcome! ;-)";
        else
            echo "Va a ser que no";
        /*
        while ($registro = $resultado->fetch_array())
        {
            echo $registro["nombre"] . " " .
                $registro["anyoNacimiento"] . "<br />";
        }
        */
    }
    $resultado->close();

}
?>  

    </body>
</html>
