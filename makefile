CONTAINER_NAME=rabbitmq
IMAGE_NAME=rabbitmq:3-management

PORTS=-p 15672:15672 -p 5672:5672

.PHONY: run
run: stop remove
	docker run -d --name $(CONTAINER_NAME) $(PORTS) $(IMAGE_NAME)

.PHONY: remove
remove:
	@echo "Verificando se o contêiner $(CONTAINER_NAME) existe..."
	@if [ $$(docker ps -aq -f name=$(CONTAINER_NAME)) ]; then \
		echo "Removendo contêiner $(CONTAINER_NAME)..."; \
		docker rm -f $(CONTAINER_NAME); \
	else \
		echo "Nenhum contêiner $(CONTAINER_NAME) encontrado."; \
	fi

.PHONY: stop
stop:
	@echo "Parando o contêiner $(CONTAINER_NAME), se estiver em execução..."
	@if [ $$(docker ps -q -f name=$(CONTAINER_NAME)) ]; then \
		docker stop $(CONTAINER_NAME); \
	else \
		echo "Nenhum contêiner $(CONTAINER_NAME) está em execução."; \
	fi
