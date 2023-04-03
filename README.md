# Inmersion-JAVA [![Aula 2](https://img.shields.io/badge/Aula%202-%F0%9F%8D%B5-blueviolet?style=for-the-badge&logo=java)](https://es.wikipedia.org/wiki/Java)

![Java](https://img.shields.io/badge/Java-007396?logo=java&logoColor=white)





# Generador de Stickers ![Cara Feliz](https://img.shields.io/badge/-😊-brightgreen?style=flat-square&logoColor=white)


Este programa genera un sticker a partir de una imagen y una calificación (obtenida del Consumo de la API AMDb). Si la calificación es mayor o igual a 7, el sticker tendrá la palabra "Good" en color blanco con borde negro en la parte inferior de la imagen. Si la calificación es menor a 7, la palabra será "Bad".


Este es un programa Java que utiliza una API para obtener los detalles de las Most Popular Movies según IMDB. El programa extrae los datos de la respuesta JSON de la API, los procesa y muestra los detalles de cada película en la consola.

## Descripcion 
El GeneradorDeStickers es una clase Java que toma como entrada una imagen en formato `InputStream`, una ruta y un nombre de archivo para la nueva imagen generada, así como una calificación numérica. La calificación se utiliza para determinar si el texto escrito en la imagen será "Good" o "Bad". 

La clase utiliza las siguientes librerías:

`java.awt.BasicStroke`
`java.awt.Color`
`java.awt.Font`
`java.awt.Graphics2D`
`java.awt.image.BufferedImage`
`java.io.File`
`java.io.InputStream`
`javax.imageio.ImageIO`

## Salida

La salida del programa muestra los detalles de cada película en la consola en formato de texto plano como en el codigo del `Aula1`
adicional al codigo del aula anterior, el `GeneradorDeStickers` crea los stickers de los posters de cada pelicula de la API IMDB y los guarda en 
la ruta especificada.


### Ejemplo de salida
![STK M3GAN](src/imagenes/STK%20M3GAN.png)
![STK THEWHALE](src/imagenes/STK%20The%20Whale.png)


