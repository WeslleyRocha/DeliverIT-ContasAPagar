package DeliverITContasAPagar.Service;

import DeliverITContasAPagar.Model.Conta;
import DeliverITContasAPagar.Repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    //Até 3 dias
    private static final BigDecimal MULTA_ATE_3_DIAS = new BigDecimal("0.02"); // 2%
    private static final BigDecimal JUROS_ATE_3_DIAS = new BigDecimal("0.001"); // 0.1%

    //Superior a >  3 dias
    private static final BigDecimal MULTA_SUPERIOR_3_DIAS = new BigDecimal("0.03"); // 3%
    private static final BigDecimal JUROS_SUPERIOR_3_DIAS = new BigDecimal("0.002"); // 0.2%

    //Superior a > = 5 dias
    private static final BigDecimal MULTA_SUPERIOR_5_DIAS = new BigDecimal("0.05"); // 5%
    private static final BigDecimal JUROS_SUPERIOR_5_DIAS = new BigDecimal("0.003"); // 0.3%

    public Conta salvar (Conta conta) {

        long dias = calcularDiasAtraso(conta);
        conta.setDiasAtraso((int) dias);

        if (dias > 0){
            calcularJurosMulta (conta, dias);
        } else{
            conta.setValorCorrigido(conta.getValorOriginal());
            conta.setRegraCalculo("Pagamento em dia, sem atraso!");
        }

        return  contaRepository.save(conta);

    }

    private long calcularDiasAtraso(Conta conta) {
        if (conta.getDataVencimento() == null || conta.getDataPagamento() == null) {
            return 0;
        }

        long diferenca = ChronoUnit.DAYS.between(conta.getDataVencimento(), conta.getDataPagamento());

        if (diferenca < 0) {
            return 0;
        }
        return diferenca;
    }

    private void calcularJurosMulta(Conta conta, long dias) {
        BigDecimal taxaMulta = null;
        BigDecimal taxaJurosDiario = null;

        if (dias > 5) {
            taxaMulta = MULTA_SUPERIOR_5_DIAS;
            taxaJurosDiario = JUROS_SUPERIOR_5_DIAS;
            conta.setRegraCalculo("Juros e Multa aplicado,Superior a 5 dias de atraso!");

        } else if (dias > 3) {
            taxaMulta = MULTA_SUPERIOR_3_DIAS;
            taxaJurosDiario = JUROS_SUPERIOR_3_DIAS;
            conta.setRegraCalculo("Juros e Multa aplicado,Superior a 3 dias de atraso!");

        } else {
            taxaMulta = MULTA_ATE_3_DIAS;
            taxaJurosDiario = JUROS_ATE_3_DIAS;
            conta.setRegraCalculo("Juros e Multa aplicado,Até 3 dias dias de atraso!");
        }

        BigDecimal valorMulta = conta.getValorOriginal().multiply(taxaMulta);

        BigDecimal valorJuros = conta.getValorOriginal()
                .multiply(taxaJurosDiario)
                .multiply(new BigDecimal(dias));

        BigDecimal total = conta.getValorOriginal()
                .add(valorMulta)
                .add(valorJuros);

        conta.setValorCorrigido(total.setScale(2, RoundingMode.HALF_EVEN));
    }

    public List<Conta> listarTodas() {
        return contaRepository.findAll();
    }
}
