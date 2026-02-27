# Simulación Spotify con Cola FIFO

Este proyecto implementa una simulación tipo Spotify utilizando una estructura de datos Cola (FIFO) desarrollada desde cero en Java. Se trabajó con usando Maven, separando la librería de la estructura de datos.

Para compilar la librería, hay que ubicarse en la carpeta "data-structure.queue" y ejecutar: mvn clean package. Para instalarla en el repositorio local de Maven, ejecutar: mvn clean install. Esto permite que el proyecto handler la utilice como dependencia.

Para compilar el proyecto consumidor, ubicarse en la carpeta "queueHandler" y ejecutar: mvn clean package. Esto generará el archivo .jar dentro de la carpeta target.

Para ejecutar desde consola, estando en "queueHandler", usar el comando:
java -cp "target\queueHandler-0.0.1-SNAPSHOT.jar;RUTA_DEL_JAR_DE_LA_LIBRERIA" umg.edu.gt.queuehandler.Main
El programa se ejecuta completamente desde CMD sin depender del IDE.

El diseño del proyecto está dividido en dos módulos: una librería que contiene la implementación manual de la cola genérica "Queue<T>" basada en nodos enlazados, y un proyecto consumidor que utiliza esta estructura para simular la reproducción de canciones.

Como decisiones técnicas, se implementó la cola enlazada para garantizar operaciones O(1) en enqueue y dequeue, se evitó el uso de estructuras del JDK, y se utilizó Maven para mantener arquitectura modular.

La prioridad se implementó usando dos colas internas: una para prioridad alta y otra para prioridad normal. Siempre se verifica primero la cola de alta prioridad, garantizando que estas canciones se reproduzcan antes que las normales, respetando el orden FIFO dentro de cada grupo.

La simulación de duración se maneja utilizando Thread.sleep(1000) para representar cada segundo de reproducción, mostrando el progreso en consola junto con una barra visual. Además, se implementó la funcionalidad de skip que permite interrumpir la canción actual presionando ENTER.

