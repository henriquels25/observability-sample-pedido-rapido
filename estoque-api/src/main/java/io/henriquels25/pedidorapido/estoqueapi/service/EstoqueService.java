package io.henriquels25.pedidorapido.estoqueapi.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class EstoqueService {

    @SneakyThrows
    public Long getEstoque(String produtoId){
        int delay = randomNumber(50, 5000);
        Thread.sleep(delay);
        return 10L;
    }

    public void reduzEstoque(Long pedidoId) {
        int random = randomNumber(1, 10);
        if (random > 8){
            log.error("Infelizmente vai ocorrer um erro");
            throw new RuntimeException("Erro generico");
        }
    }

    private int randomNumber(int min, int max){
        Random rand = new Random();
        int randomNumber = rand.nextInt(max - min + 1) + min;
        return randomNumber;
    }
}
