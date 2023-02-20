# Coordinadora

## Arquitectura:
- Implemente una arquitectura limpia basado en el Libro de Robert C Martin, donde se mantienen aisladas las capas para una mejor escalabilidad del codigo.
- Aplique los principios S.O.L.I.D para mantener la eficiencia y escalabilidad el proyecto
- MVVM para la capa de presentacion

[![N|Clean](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)]


# APK:
Se encuentra en el directorio raiz coordinadora-app.apk

# Tecnogologias y herramientas:
- HILT: Para injeccion de dependencias use 
- Retrofit: consumir la API
- Room: Base de datos local
- Compose: construccion de la UI

# Videos explicacion

Arquitectura : https://www.loom.com/share/c5b8e52cc59e4db5a5740f4478128286
Demo app: https://www.loom.com/share/de26d1fb2852429c86b814d9de20b476


# Algunas consideraciones finales
- Por cuestiones de tiempo no realice la opcion de los mapas, sin embargo se como hacerlo
- Hice unos pocos tests para demostrar como los realizo, pero no lo hice para todas las clases (como se debe en un proyecto real) por cuestiones de tiempo y priorizando el funcionamiento mas importante
- Quise arriesgarme a usar compose para la UI aunque me tomara algo mas de tiempo, pero creo que fue buen resultado y mejora el performance
- bloquee la pantalla a portrait, igualmente por cuestiones de tiempo, ya que para que funcione en modo landscape hay que diseñar la pantalla
