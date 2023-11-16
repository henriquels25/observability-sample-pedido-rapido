package io.henriquels25.pedidorapido.pedidosapi.repository;

import io.henriquels25.pedidorapido.pedidosapi.controller.ItemPedido;
import io.henriquels25.pedidorapido.pedidosapi.controller.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoEntity PedidoToPedidoEntity(Pedido pedido);
    ItemPedidoEntity map(ItemPedido itemPedido);
}
