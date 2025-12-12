import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContaService } from '../../services/conta.service';
import { Conta } from '../../models/conta.model';

@Component({
  selector: 'app-contas-lista',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './contas-lista.component.html',
  styleUrl: './contas-lista.component.css'
})
export class ContasListaComponent implements OnInit {

  contas: Conta[] = [];

  constructor(private contaService: ContaService) {}

  ngOnInit(): void {
    this.carregarContas();
  }

  carregarContas() {
    this.contaService.listar().subscribe({
      next: (dados) => {
        this.contas = dados;
        console.log('Contas carregadas:', dados);
      },
      error: (erro) => {
        console.error('Erro ao buscar contas', erro);
        alert('Erro ao carregar a lista.');
      }
    });
  }
}
