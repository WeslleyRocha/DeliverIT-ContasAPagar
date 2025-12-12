import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContasCadastro } from './contas-cadastro';

describe('ContasCadastro', () => {
  let component: ContasCadastro;
  let fixture: ComponentFixture<ContasCadastro>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContasCadastro]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContasCadastro);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
