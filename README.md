# Observability Sample: Pedido Rápido

## Visão Geral
Este repositório demonstra a implementação de observabilidade em microserviços utilizando Jaeger e Graylog para tracing e logs. Ele contém dois microserviços principais: `estoque-api` e `pedido-api`. Estes serviços são instrumentados para enviar dados de rastreamento e logs para o Jaeger e Graylog, respectivamente, permitindo uma observação detalhada de suas operações.

## Arquitetura
- **estoque-api**: Gerencia o estoque de produtos.
- **pedido-api**: Lida com a criação e gerenciamento de pedidos.
- **Kafka**: Facilita a comunicação orientada a eventos entre os microserviços.


## Comportamento dos Microserviços e Interatividade
- **Criação de Pedidos**: Quando um pedido é criado, o `pedido-api` verifica o estoque para cada item do pedido com o `estoque-api`. Se algum item não estiver disponível, um erro é retornado ao cliente.
- **Processamento e Comunicação**: Após a verificação de estoque, o pedido é registrado no banco de dados e uma mensagem é enviada ao tópico `pedidos-criados-v1` do Kafka.
- **Consumo de Mensagens**: A `estoque-api` consome mensagens desse tópico e realiza a atualização do estoque.
- **Simulação de Comportamento Realista**: Tanto no `estoque-api` quanto no `pedido-api`, há um atraso randômico nas respostas (entre 500ms e 5 segundos) e uma taxa de erro de 20%. Esta simulação é intencional para a visualização eficaz dos tracings e logs de erros ou lentidão.

## Quick Start
### Pré-Requisitos
- Docker e Docker Compose instalados.

### Instruções de Uso
1. Clone o repositório:

```git clone https://github.com/henriquels25/observability-sample-pedido-rapido```

2. Navegue até a pasta do repositório:

`cd observability-sample-pedido-rapido`

3. Inicie os serviços com Docker Compose:

`docker-compose up -d`

Isso levantará o Kafka, o Jaeger e o Graylog.

4. Acesse a UI do Jaeger e Graylog:
    - Jaeger: `http://localhost:16686`
    - Graylog: `http://localhost:9000`
    - Dados de autenticação do Graylog:
        - **Usuário**: admin
        - **Senha**: admin12345678910

5. Criar Input Gelf TCP no graylog com URL de bingding `0.0.0.0`.

### Configurações
Ao iniciar os microserviços no perfil `default`, eles já estão configurados para enviar dados para o Jaeger e Graylog.

## Exemplos de Chamadas

Esta seção oferece exemplos de chamadas para interagir com os microserviços `pedido-api` e `estoque-api`. As chamadas a seguir demonstram como criar um pedido e consultar um produto, respectivamente.

### Criar Pedido
Para criar um novo pedido, faça uma requisição POST para `pedido-api` com os detalhes do pedido.

**Endpoint**: `POST http://localhost:8080/pedidos`

**Corpo da Requisição**:
```json
{
    "id": "5",
    "clienteId": "3",
    "itens": [
        {
            "produtoId": "PR1",
            "quantidade": 1
        },
        {
            "produtoId": "PR2",
            "quantidade": 1
        }
    ],
    "enderecoEntrega": "Rua Teste, 1"
}
```

### Consultar Estoque de Produto
Para consultar o estoque de um produto específico, faça uma requisição GET para estoque-api.

**Endpoint**: `GET http://localhost:8081/produtos/1`

Essas chamadas ajudarão a interagir com os serviços e observar como os dados são processados e registrados em termos de tracing e logs.

## Contribuições
Contribuições são bem-vindas! Para contribuir, por favor, siga os passos:
1. Faça um fork do repositório.
2. Crie uma nova branch para suas modificações.
3. Faça suas alterações.
4. Envie um pull request.

## Licença
Este projeto está licenciado sob a [Licença MIT](LICENSE).