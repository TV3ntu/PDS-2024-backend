# PDS-2024-backend
Proyecto de software 2024 - Backend

## GitFlow en nuestro Proyecto

Este repositorio sigue el modelo de branching GitFlow para gestionar el desarrollo de software. GitFlow es un flujo de trabajo popular en Git que define una estructura de ramificación para proyectos de software.

### Ramas Disponibles

- **master**: La rama master refleja el estado de producción estable. Los commits en esta rama representan versiones de software listas para ser implementadas en producción.

- **develop**: La rama develop es la rama base para integrar todas las características en progreso. Es donde se fusionan todas las características completadas antes de ser liberadas en producción.

- **feature/**: Las ramas de características se utilizan para desarrollar nuevas funcionalidades. Cada nueva característica debe tener su propia rama de características, que se bifurca de develop y se fusiona de vuelta en develop una vez completada.

- **fix/**: Las ramas de fix se utilizan para corregir problemas. Se crean a partir de la rama develop y se fusionan de vuelta en develop una vez solucionados los problemas.
