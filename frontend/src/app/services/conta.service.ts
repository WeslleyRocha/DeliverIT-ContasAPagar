import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Conta } from '../models/conta.model';

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  private apiUrl = 'http://localhost:8080/contas';

  constructor(private http: HttpClient) { }

  //Método para Salvar (POST)
  salvar(conta: Conta): Observable<Conta> {
    return this.http.post<Conta>(this.apiUrl, conta);
  }

  //Método para Listar (GET)
  listar(): Observable<Conta[]> {
    return this.http.get<Conta[]>(this.apiUrl);
  }

}
