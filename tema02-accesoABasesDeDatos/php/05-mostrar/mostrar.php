<!doctype html>
<html>
    <head>
        <title>Mostrar datos</title>
        <meta charset="utf-8" />
    </head>
    
    <body>
        <h1>Mostrar datos</h1>
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
            
            $mysqli->query("SET NAMES utf8;");

            if ($resultado = $mysqli->query(
                "SELECT * FROM alumnos"))
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
