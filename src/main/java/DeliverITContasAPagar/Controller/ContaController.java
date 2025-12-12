package DeliverITContasAPagar.Controller;

import DeliverITContasAPagar.Model.Conta;
import DeliverITContasAPagar.Service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "*")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public ResponseEntity<List<Conta>> listarTodas() {

        List<Conta> contas = contaService.listarTodas();

        return new ResponseEntity<>(contas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Conta> salvarConta(@Valid @RequestBody Conta conta){

        try {
            Conta contaSalva = contaService.salvar(conta);

            return new ResponseEntity<>(contaSalva, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
