import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ContasCadastroComponent } from './components/contas-cadastro/contas-cadastro.component';
import { ContasListaComponent } from './components/contas-lista/contas-lista.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ContasCadastroComponent, ContasListaComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
