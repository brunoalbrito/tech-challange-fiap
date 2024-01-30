# Tech Challange - FIAP

[![codecov](https://codecov.io/gh/brunoalbrito/tech-challange-fiap/graph/badge.svg?token=FW8FL3RML6)](https://codecov.io/gh/brunoalbrito/tech-challange-fiap)


## Como executar o projeto

```bash
docker compose -f compose-prod.yaml up
```

## Documentação API - Postman

https://documenter.getpostman.com/view/30346053/2s9YXb95pc

## Documentação API - Swagger

https://brunoalbrito.github.io/tech-challange-fiap/

## Notion com as informações do projeto

https://tropical-angelfish-eaf.notion.site/Tech-Challenge-FIAP-2a8d164901e54f4e87d6763425323376


# Documentação de Configuração e Uso

Esta documentação fornece instruções passo a passo para configurar o ambiente Minikube, implantar aplicativos e acessar serviços em um cluster Kubernetes local. Os seguintes procedimentos detalham as etapas necessárias:

## 1. Habilitar Métricas do Minikube

Para habilitar a coleta de métricas no Minikube, execute os seguintes comandos:

```bash
minikube addons list
minikube addons enable metrics-server
```

## 2. Iniciar o Minikube

Para iniciar o Minikube, utilize o seguinte comando:

```bash
minikube start
```

## 3. Utilizar o Daemon Docker dentro da Instância do Minikube

Para configurar o uso do daemon Docker dentro da instância do Minikube, execute o seguinte comando:

```bash
eval $(minikube docker-env)
```

## 4. Gerar a Imagem do Projeto

Para construir a imagem do projeto no diretório do projeto, utilize o seguinte comando:

```bash
docker build --no-cache -t tech-challenge .
```

## 5. Executar os Scripts de Implantação

Para implantar os recursos necessários no Kubernetes, execute os seguintes comandos:

```bash
kubectl apply -f postgres-configmap-1.yaml
kubectl apply -f postgres-pvc-pv-2.yaml
kubectl apply -f postgres-deployment-3.yaml
kubectl apply -f postgres-service-4.yaml
kubectl apply -f tech-challenge-fiap-deployment-5.yaml
kubectl apply -f tech-challenge-fiap-service-6.yaml
kubectl apply -f hpa.yaml
```

## 6. Conectar o DBeaver ao PostgreSQL

Para conectar o DBeaver ao PostgreSQL implantado no Kubernetes, execute os seguintes comandos:

```bash
kubectl get pods
kubectl port-forward pod/[pod-name] 5432:5432
```

Após isso, conecte o DBeaver à seguinte URL:

```
localhost:5432/postgres
```

Use as seguintes credenciais:
- **Usuário**: myuser
- **Senha**: secret

## 7. Expor a Porta 80 do Container da Aplicação

Para acessar a aplicação via porta 8080, execute o seguinte comando:

```bash
kubectl port-forward pod/[pod-name] 8080:8080
```

Após isso, você pode acessar a seguinte URL para acessar a API de combos:

```
localhost:8080/api/combos
```

# Arquitetura
<img src="/ArquiteturaSistema.drawio.png">
<img src="/ArquiteturaInfraestrutura.drawio.png">
