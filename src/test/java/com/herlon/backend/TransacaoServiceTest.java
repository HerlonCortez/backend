package com.herlon.backend;

import com.herlon.backend.domain.entity.Transacao;
import com.herlon.backend.domain.repository.TransacaoRepository;
import com.herlon.backend.domain.sevices.TransacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService service;

    @Mock
    private TransacaoRepository repository;

    @Test
    public void testListTotaisTransacoesNomeDaLoja(){
        //AAA
        final String lojaA = "lojaA", lojaB = "lojaB";
        var transacao = new Transacao(
                1L,1,
                 new Date(System.currentTimeMillis()),
                 BigDecimal.valueOf(50),
                987654321L,
                "9876-5432-1098-7654",
                new Time(System.currentTimeMillis()),
                "Dono da Loja A",
                lojaA
        );

        var transacao2 = new Transacao(
                2L,1,
                new Date(System.currentTimeMillis()),
                BigDecimal.valueOf(100),
                123456789L,
                "1234-5678-9012-3456",
                new Time(System.currentTimeMillis()),
                "Dono da LOja B",
                lojaB
        );

        var transacao3 = new Transacao(
                3L,1,
                new Date(System.currentTimeMillis()),
                BigDecimal.valueOf(75),
                111222333L,
                "1111-2222-3333-4444",
                new Time(System.currentTimeMillis()),
                "Dono da LOja A",
                lojaA
        );

        var mockTransacoes = List.of(transacao, transacao2, transacao3);
        when(repository.findAllByOrderByNomeDaLojaAscIdDesc())
                .thenReturn(mockTransacoes);

        var reports = service.listTotaisTransacoesPorNomeLoja();

        assertEquals(2, reports.size());

        reports.forEach(report ->{
            if(report.nomeDaLoja().equals(lojaA)){
                assertEquals(2, report.transacoes().size());
                assertEquals(BigDecimal.valueOf(125), report.total());
                assertTrue(report.transacoes().contains(transacao));
                assertTrue(report.transacoes().contains(transacao3));
            } else if (report.nomeDaLoja().equals(lojaB)) {
                assertEquals(1, report.transacoes().size());
                assertEquals(BigDecimal.valueOf(100), report.total());
                assertTrue(report.transacoes().contains(transacao2));
            }
        });
    }
}
