import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContasLista } from './contas-lista';

describe('ContasLista', () => {
  let component: ContasLista;
  let fixture: ComponentFixture<ContasLista>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContasLista]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContasLista);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
