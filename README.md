# Microservices-v2
Learning Microservices wit Spring Boot &amp; Spring Cloud and containerizing using Docker and orchestrating using Kubernetes

Extra learnings apart from [Microservices-v1](https://github.com/SyedAanif/Microservices-v1)

1. Docker
2. Kubernetes
3. Spring Boot 2.4.x+ & Spring Cloud 2020.x+
4. Service Registry using Eureka Naming Server
5. Load Balancing with Spring Cloud LoadBalancer (replacing Ribbon)
6. API Gateway with Spring Cloud Gateway (instead of Zuul)
7. Circuit Breaker with Resilience4j (instead of Hystrix)
8. Distributed Tracing with Zipkin
9. Asynchronous Communication using Rabbit MQ

Docker Commands
```
docker run -p 9411:9411 openzipkin/zipkin:2.23
docker push docker.io/syedaanif/ms-v2-currency-exchange-service:0.0.1-SNAPSHOT
docker-compose --version
docker-compose up
docker push syedaanif/ms-v2-naming-server:0.0.1-SNAPSHOT
docker push syedaanif/ms-v2-currency-conversion-service:0.0.1-SNAPSHOT
docker push syedaanif/ms-v2-api-gateway:0.0.1-SNAPSHOT
```
[Docker Repository](https://hub.docker.com/u/syedaanif) to download images from.

Kubernetes Commands
```
kubectl get events
kubectl get pods
kubectl get replicaset
kubectl get deployment
kubectl get service

kubectl explain pods
kubectl get pods -o wide

kubectl get events --sort.by=.metadata.creationTimestamp

kubectl get rs
kubectl get rs -o wide

docker login
docker push syedaanif/ms-v2-currency-exchange-service:0.0.1-SNAPSHOT
docker push syedaanif/ms-v2-currency-conversion-service:0.0.1-SNAPSHOT

kubectl create deployment currency-exchange --image=syedaanif/ms-v2-currency-exchange-service:0.0.1-SNAPSHOT
kubectl expose deployment currency-exchange --type=LoadBalancer --port=8000

kubectl create deployment currency-conversion --image=syedaanif/ms-v2-currency-conversion-service:0.0.11-SNAPSHOT
kubectl expose deployment currency-conversion --type=LoadBalancer --port=8100


kubectl get deployment currency-exchange -o yaml >> deployment.yaml 
kubectl get service currency-exchange -o yaml >> service.yaml 

kubectl diff -f deployment.yaml
kubectl apply -f deployment.yaml

kubectl delete all -l app=currency-exchange
kubectl delete all -l app=currency-conversion

kubectl rollout history deployment currency-conversion
kubectl rollout history deployment currency-exchange
kubectl rollout undo deployment currency-exchange --to-revision=1

kubectl logs currency-exchange-9fc6f979b-2gmn8
kubectl logs -f currency-exchange-9fc6f979b-2gmn8 

kubectl autoscale deployment currency-exchange --min=1 --max=3 --cpu-percent=5 
kubectl get hpa

kubectl top pod
kubectl top nodes
kubectl get hpa
kubectl delete hpa currency-exchange

kubectl create configmap currency-conversion --from-literal=CURRENCY_EXCHANGE_URI=http://currency-exchange
kubectl get configmap

kubectl get configmap currency-conversion -o yaml >> configmap.yaml
```
Ports
| Application |	Port |
| ----------- | ---- |
| Limits Service | 8080, 8081, ... |
| Spring Cloud Config Server |	8888 |
| Currency Exchange Service |	8000, 8001, 8002, .. |
| Currency Conversion Service |	8100, 8101, 8102, ... |
| Netflix Eureka Naming Server |	8761 |
| Netflix Zuul API Gateway Server |	8765 |
| Zipkin Distributed Tracing Server |	9411 |

Zipkin Installation
Quick Start Page

https://zipkin.io/pages/quickstart

Downloading Zipkin Jar


https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec

Command to run
```
RABBIT_URI=amqp://localhost java -jar zipkin-server-2.12.9-exec.jar 
```
