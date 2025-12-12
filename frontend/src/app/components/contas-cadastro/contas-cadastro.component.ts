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

  constructor(
    private contaService: ContaService,
    private router: Router
  ) {}

  cadastrar() {
    console.log('Enviando conta:', this.conta);

    this.contaService.salvar(this.conta).subscribe({
      next: (resposta) => {
        alert('Conta cadastrada com sucesso!');
      },

      error: (erro) => {
        console.error('Erro ao salvar:', erro);
        alert('Erro ao salvar conta. Verifique os dados.');
      }
    });
  }
}
