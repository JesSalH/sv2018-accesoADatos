<!doctype html>
<html>
    <head>
        <title>Resultados de la búsqueda</title>
        <meta charset="utf-8" />
    </head>
    
    <body>
        <h1>Resultados de la búsqueda</h1>
        <p>
            <?php
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
            
            if (! isset($_POST["nombre"])) 
            {
                echo "Faltan detalles de búsqueda";
                echo "</p></body></html>";
                exit();
            }
            
            $mysqli->query("SET NAMES utf8;");

            $busqueda = $_POST["nombre"];
            $orden = "SELECT * FROM alumnos
                WHERE nombre LIKE '%". $busqueda ."%'";
            // echo $orden;
            if ($resultado = $mysqli->query($orden))
            {
                echo "Cantidad de filas encontradas: " . 
                    $resultado->num_rows . "<br />";
                    
                while ($registro = $resultado->fetch_array())
                {
                    echo $registro["nombre"] . " " .
                        $registro["anyoNacimiento"] . "<br />";
                }
            }
            $resultado->close();
           ?>
        </p>
    </body>
</html>
