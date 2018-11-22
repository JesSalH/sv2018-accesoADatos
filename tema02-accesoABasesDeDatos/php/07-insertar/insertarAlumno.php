<!doctype html>
<html>
    <head>
        <title>Registro</title>
        <meta charset="utf-8" />
    </head>
    
    <body>
        <h1>Registro</h1>
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
                        
            if (!isset($_POST["nombre"]) || !isset($_POST["anyo"])) 
            {
                echo "Faltan detalles";
                echo "</p></body></html>";
                exit();
            }
            
            $mysqli->query("SET NAMES utf8;");
            $nombre = $_POST["nombre"];
            $anyo = $_POST["anyo"];

            $orden = "INSERT INTO ALUMNOS VALUES (NULL, '".
                $nombre."', ".$anyo.")";
            
            $resultado = $mysqli->query($orden)
            if($resultado == 1)
                echo "Inserción realizada correctamente";
            else
                echo "No se ha podido insertar"; 

            $resultado->close();
           ?>
        </p>
    </body>
</html>
