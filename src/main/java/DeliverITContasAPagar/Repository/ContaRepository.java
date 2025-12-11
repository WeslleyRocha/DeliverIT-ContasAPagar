package DeliverITContasAPagar.Repository;

import DeliverITContasAPagar.Model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
