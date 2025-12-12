import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ContaService } from '../../services/conta.service';
import { Conta } from '../../models/conta.model';

@Component({
  selector: 'app-contas-cadastro',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './contas-cadastro.component.html',
  styleUrl: './contas-cadastro.component.css'
})
export class ContasCadastroComponent {

  conta: Conta = {
    nome: '',
    valorOriginal: 0,
    dataVencimento: '',
    dataPagamento: ''
  };

  // Variável para segurar o texto formatado (Ex: "1.500,00")
  valorInput: string = '';

  constructor(
    private contaService: ContaService,
    private router: Router
  ) {}

  // Formata o valor visualmente enquanto o usuário digita
  formatarMoeda(event: any) {
    let valor = event.target.value;

    // Remove tudo que não é dígito
    valor = valor.replace(/\D/g, "");

    // Divide por 100 para colocar as casas decimais (Ex: 150000 -> 1500.00)
    valor = (Number(valor) / 100).toFixed(2) + "";

    // Troca ponto por vírgula
    valor = valor.replace(".", ",");

    // Adiciona ponto dos milhares (Ex: 1500,00 -> 1.500,00)
    valor = valor.replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.");

    // Atualiza o input
    this.valorInput = valor;
  }

  cadastrar() {
    //Transforma "1.500,00" em 1500.00 (double) para o Java
    if (this.valorInput) {
      const valorLimpo = this.valorInput.replace(/\./g, '').replace(',', '.');
      this.conta.valorOriginal = parseFloat(valorLimpo);
    }

    console.log('Enviando conta:', this.conta);

    this.contaService.salvar(this.conta).subscribe({
      next: (resposta) => {
        alert('Conta cadastrada com sucesso!');

        // Recarrega a página
        window.location.reload();

        // Opcional: Limpar o form
        this.conta = { nome: '', valorOriginal: 0, dataVencimento: '', dataPagamento: '' };
        this.valorInput = '';
      },
      error: (erro) => {
        console.error('Erro ao salvar:', erro);
        alert('Erro ao salvar conta. Verifique os dados.');
      }
    });
  }
}
