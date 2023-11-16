package io.henriquels25.pedidorapido.pedidosapi.controller;

import java.util.List;

public record Pedido(String clienteId, List<ItemPedido> itens, String enderecoEntrega) {
}
