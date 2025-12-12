export interface Conta {
    id?: number;
    nome: string;
    valorOriginal: number;
    dataVencimento: string;
    dataPagamento: string;

    valorCorrigido?: number;
    diasAtraso?: number;
    regraCalculo?: string;
}
