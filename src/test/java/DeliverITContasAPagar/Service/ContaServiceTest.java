package DeliverITContasAPagar.Service;

import DeliverITContasAPagar.Model.Conta;
import DeliverITContasAPagar.Repository.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @Test
    void  givenPagamentoEmDia_whenSalvar_thenSemJurosNemMulta() {

        Conta criarConta = criarConta("Teste - Criar Conta",100, "2025-11-12", "2025-11-12");

        Mockito.when(contaRepository.save(criarConta)).thenReturn(criarConta);

        Conta contaSalva = contaService.salvar(criarConta);

        Assertions.assertEquals(0, contaSalva.getDiasAtraso());
        Assertions.assertEquals(new BigDecimal("100"), contaSalva.getValorCorrigido());
        Assertions.assertEquals("Pagamento em dia, sem atraso!", contaSalva.getRegraCalculo());
    }

    @Test
    void  givenAtrasoDe3Dias_whenSalvar_thenAplicaRegraAte3Dias() {

        Conta criarConta = criarConta("Teste - Criar Conta",100, "2025-11-10", "2025-11-13");

        Mockito.when(contaRepository.save(criarConta)).thenReturn(criarConta);

        Conta contaSalva = contaService.salvar(criarConta);

        Assertions.assertEquals(3, contaSalva.getDiasAtraso());
        Assertions.assertEquals(new BigDecimal("102.30"), contaSalva.getValorCorrigido());
        Assertions.assertEquals("Juros e Multa aplicado,Até 3 dias dias de atraso!", contaSalva.getRegraCalculo());
    }

    @Test
    void  givenAtrasoDe6Dias_whenSalvar_thenAplicaRegraSuperiorA5Dias() {

        Conta criarConta = criarConta("Teste - Criar Conta",100, "2025-11-10", "2025-11-16");

        Mockito.when(contaRepository.save(criarConta)).thenReturn(criarConta);

        Conta contaSalva = contaService.salvar(criarConta);

        Assertions.assertEquals(6, contaSalva.getDiasAtraso());
        Assertions.assertEquals(new BigDecimal("106.80"), contaSalva.getValorCorrigido());
        Assertions.assertEquals("Juros e Multa aplicado,Superior a 5 dias de atraso!", contaSalva.getRegraCalculo());
    }

    private Conta criarConta(String nome, double valor, String dataVencimento, String dataPagamento) {
        Conta conta = new Conta();

        conta.setNome(nome);
        conta.setValorOriginal(new BigDecimal(valor));
        conta.setDataVencimento(java.time.LocalDate.parse(dataVencimento));
        conta.setDataPagamento(java.time.LocalDate.parse(dataPagamento));
        return conta;
    }

}
