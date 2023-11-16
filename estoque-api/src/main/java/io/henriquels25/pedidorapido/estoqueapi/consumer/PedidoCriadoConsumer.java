package io.henriquels25.pedidorapido.estoqueapi.consumer;

import io.henriquels25.pedidorapido.estoqueapi.service.EstoqueService;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component("pedidoCriadoConsumer")
@RequiredArgsConstructor
@Slf4j
public class PedidoCriadoConsumer implements Consumer<PedidoCriadoMessage> {

    private final EstoqueService estoqueService;
    private final Tracer tracer;

    @Override
    public void accept(PedidoCriadoMessage pedidoCriadoMessage) {
        try {
            estoqueService.reduzEstoque(pedidoCriadoMessage.pedidoId());
        } catch (RuntimeException ex){
            log.error("Ocorreu um erro inesperado, marcando trace como erro");
            tracer.currentSpan().error(ex);
        }
    }
}
