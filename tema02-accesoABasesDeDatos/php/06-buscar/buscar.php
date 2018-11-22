<!doctype html>
<html>
    <head>
        <title>Buscar datos</title>
        <meta charset="utf-8" />
    </head>
    
    <body>
        <h1>Buscar datos</h1>
        <form action="resultadosDeLaBusqueda.php" method="post"> 
            <input type="text" name="nombre" placeholder="Nombre" />
            <br />
            <?php /*
            <input type="text" name="anyo" placeholder="Año de nacimiento" />
            <br />
            */ ?>
            <input type="submit" value="Buscar" />
        </form>
    </body>
</html>
