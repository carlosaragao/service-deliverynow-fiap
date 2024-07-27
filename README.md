# FIAP - SOAT7 🚀
## Team 95 - Delivery Now
```
🍔 System Fast Food 
```
---
## | 👊🏽 • Team 95
| |\Name|\Identity|
|-|-|-|
| 🐰 | Leandro Coelho | RM355527 |
---

## | 🖥️ • Event Storming
- https://miro.com/miroverse/sistema-de-delivery/?social=copy-link

## | ✉️ • Deploying

# service-food
Este projeto usa Quarkus, e Java 21

## Executando a aplicação utilizando o docker

1. Construir a imagem da aplicação executando o comando abaixo

```shell script
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/service-food-jvm .
```
3. Executar o comando para subir a imagem do mongo e da aplicação

```shell script
docker-compose up -d
```
4. Link do Swagger
- http://localhost:8080/service-deliverynow-fiap/q/swagger-ui/
