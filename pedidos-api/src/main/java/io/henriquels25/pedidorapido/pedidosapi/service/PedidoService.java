package io.henriquels25.pedidorapido.pedidosapi.service;

import io.henriquels25.pedidorapido.pedidosapi.client.EstoqueClient;
import io.henriquels25.pedidorapido.pedidosapi.controller.Pedido;
import io.henriquels25.pedidorapido.pedidosapi.kafka.PedidoCriadoProducer;
import io.henriquels25.pedidorapido.pedidosapi.repository.PedidoRepository;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final EstoqueClient estoqueClient;
    private final PedidoCriadoProducer pedidoCriadoProducer;
    private final PedidoRepository pedidoRepository;
    private final Tracer tracer;


    @SneakyThrows
    public void cria(Pedido pedido){
        log.info("Iniciando criação de pedido");

        int delay = randomNumber(50, 5000);
        Thread.sleep(delay);
        for (var item : pedido.itens()) {
           log.trace("Fazendo requisição de estoque para produto {}", item.produtoId());
           Long estoqueAtual = estoqueClient.get(item.produtoId()).quantidade();
           if (item.quantidade() > estoqueAtual){
               throw new EstoqueExcedidoException("O produto " + item.produtoId() + " não está mais no estoque");
           }
        }
        int random = randomNumber(1, 10);
        if (random > 9){
            log.error("Infelizmente vai ocorrer um erro");
            throw new RuntimeException("Erro generico");
        }
        tracer.currentSpan().tag("cliente-id", pedido.clienteId());
        Long pedidoId = pedidoRepository.save(pedido);
        pedidoCriadoProducer.sendPedidoCriadoMessage(pedidoId);
    }

    private int randomNumber(int min, int max){
        Random rand = new Random();
        int randomNumber = rand.nextInt(max - min + 1) + min;
        return randomNumber;
    }

}
