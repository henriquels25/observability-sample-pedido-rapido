package io.henriquels25.pedidorapido.pedidosapi.service;

public class EstoqueExcedidoException extends RuntimeException {
    public EstoqueExcedidoException(String s) {
        super(s);
    }
}
