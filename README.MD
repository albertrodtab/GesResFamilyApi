# GesResFamily

Proyecto de la asignatura de acceso a datos de 2º de DAM de San Valero.

El proyecto pretende construir una API para poder ser consumida por una aplicación android de comunicación con familias
para un centro residencial de atención a personas mayores.

### Requisitos (1 pto cada uno, obligatorios)

* El modelo de datos estará compuesto de, al menos, 5 clases y tendrán que existir relaciones entre ellas. 
Cada clase tendrá, al menos, 6 atributos (String, int, float, boolean y algún tipo para almacenar fechas). Cada clase
tendrá, al menos, 2 atributos obligatorios y algún otro con algún tipo de restricción de formato/validación.
* Se tendrá que poder realizar, al menos, las operaciones CRUD sobre cada una de las clases. Se controlarán, al menos, 
los errores 400, 404 y 500.
* Añade opciones de filtrado para al menos una operación en cada clase en donde se puedan indicar hasta 3 campos 
diferentes (solo aplicable para operaciones GET).
* Prepara una colección Postman que permita probar todas las operaciones desarrolladas.
* Configura en el proyecto la librería logback para que la aplicación web cuente con un log. Añade trazas en el código 
de forma que permita seguir el rastro de ejecución en el log (para todas las operaciones que se puedan realizar y 
también para los casos en los que se recojan errores).

### Otras funcionalidades (1 pto cada una)
##### *IMPLEMENTADO*
* Añade una operación PATCH para cada una de las clases del modelo

##### *IMPLEMENTADO*
* Utiliza la herramienta Git (y GitHub) durante todo el desarrollo de la aplicación. Escribe el fichero README.md para 
explicar cómo poner en marcha el proyecto. Utiliza el gestor de Issues para los problemas/fallos que vayan surgiendo

* Añade 3 nuevos endpoints a la aplicación (sin repetir método) que realicen nuevas operaciones con los datos y que 
requieran el uso de DTOs y/o utilizar las relaciones entre las clases

* Securiza algunas de tus operaciones de la API con un token JWT

* Añade 3 operaciones que utilicen consultas JPQL para extraer la información de la base de datos

* Añade 3 operaciones que utilicen consultas SQL nativas para extraer la información de la base de datos

* Añade 2 clases más al modelo de datos con sus respectivas operaciones CRUD (inclúyelas también en la colección Postman)

* Parametriza la colección Postman para que pueda ser ejecutada con el Runner de Postman y realizar una 
prueba completa de la API

