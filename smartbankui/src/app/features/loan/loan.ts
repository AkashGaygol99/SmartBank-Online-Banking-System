import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoanService } from '../../core/services/loan';

@Component({
  selector: 'app-loan',
  imports: [FormsModule, CommonModule, DatePipe],
  templateUrl: './loan.html',
  styleUrl: './loan.css',
})
export class Loan implements OnInit {

  loanData = {
    amount: '',
    purpose: '',
    tenureMonths: ''
  };

  loans: any[] = [];
  successMessage = '';
  errorMessage = '';

  constructor(
    private loanService: LoanService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadLoans();
  }

  loadLoans(): void {
    this.loanService.myLoans().subscribe({
      next: (res) => {
        console.log('LOANS RESPONSE:', res);
        this.loans = res;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log('LOAN LOAD ERROR:', err);
        this.errorMessage = 'Unable to load loan details';
        this.cdr.detectChanges();
      }
    });
  }

  applyLoan(): void {
    this.successMessage = '';
    this.errorMessage = '';

    const payload = {
      amount: Number(this.loanData.amount),
      purpose: this.loanData.purpose,
      tenureMonths: Number(this.loanData.tenureMonths)
    };

    this.loanService.applyLoan(payload).subscribe({
      next: () => {
        this.successMessage = 'Loan application submitted successfully';
        this.loanData = {
          amount: '',
          purpose: '',
          tenureMonths: ''
        };
        this.loadLoans();
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log('LOAN ERROR:', err.error);
        this.errorMessage = err.error?.message || 'Loan application failed';
        this.cdr.detectChanges();
      }
    });
  }
}