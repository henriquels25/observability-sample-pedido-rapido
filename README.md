# Observability Sample: Pedido Rápido

## Visão Geral
Este repositório demonstra a implementação de observabilidade em microserviços utilizando Jaeger e Graylog para tracing e logs. Ele contém dois microserviços principais: `estoque-api` e `pedido-api`. Estes serviços são instrumentados para enviar dados de rastreamento e logs para o Jaeger e Graylog, respectivamente, permitindo uma observação detalhada de suas operações.

## Arquitetura
- **estoque-api**: Gerencia o estoque de produtos.
- **pedido-api**: Lida com a criação e gerenciamento de pedidos.
- **Kafka**: Facilita a comunicação orientada a eventos entre os microserviços.

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

Os dados de autenticação do Graylog são os seguintes:

**Usuário**: admin

**Senha**: admin12345678910

5. Criar Input Gelf TCP no graylog com URL de bingding `0.0.0.0`.

### Configurações
Ao iniciar os microserviços no perfil `default`, eles já estão configurados para enviar dados para o Jaeger e Graylog.

## Contribuições
Contribuições são bem-vindas! Para contribuir, por favor, siga os passos:
1. Faça um fork do repositório.
2. Crie uma nova branch para suas modificações.
3. Faça suas alterações.
4. Envie um pull request.

## Licença
Este projeto está licenciado sob a [Licença MIT](LICENSE).