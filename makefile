# Nome do contêiner e imagem
CONTAINER_NAME=rabbitmq
IMAGE_NAME=rabbitmq:3-management

# Portas mapeadas
PORTS=-p 15672:15672 -p 5672:5672

# Alvo principal: remove e recria o contêiner
.PHONY: run
run: stop remove
	docker run -d --name $(CONTAINER_NAME) $(PORTS) $(IMAGE_NAME)

# Remove o contêiner antigo, se existir
.PHONY: remove
remove:
	@echo "Verificando se o contêiner $(CONTAINER_NAME) existe..."
	@if [ $$(docker ps -aq -f name=$(CONTAINER_NAME)) ]; then \
		echo "Removendo contêiner $(CONTAINER_NAME)..."; \
		docker rm -f $(CONTAINER_NAME); \
	else \
		echo "Nenhum contêiner $(CONTAINER_NAME) encontrado."; \
	fi

# Para o contêiner, se estiver rodando
.PHONY: stop
stop:
	@echo "Parando o contêiner $(CONTAINER_NAME), se estiver em execução..."
	@if [ $$(docker ps -q -f name=$(CONTAINER_NAME)) ]; then \
		docker stop $(CONTAINER_NAME); \
	else \
		echo "Nenhum contêiner $(CONTAINER_NAME) está em execução."; \
	fi
