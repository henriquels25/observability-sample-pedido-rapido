package io.henriquels25.pedidorapido.estoqueapi.controller;

import io.henriquels25.pedidorapido.estoqueapi.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Slf4j
public class EstoqueController {

    private final EstoqueService estoqueService;

    @GetMapping("/{id}")
    public EstoqueResponse buscaEstoque(@PathVariable("id") String produtoId) {
        log.info("obtendo estoque do produto {}", produtoId);
        Long estoque = estoqueService.getEstoque(produtoId);
        return new EstoqueResponse(produtoId, estoque);
    }


}
