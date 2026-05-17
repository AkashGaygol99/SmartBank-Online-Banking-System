import { CommonModule } from '@angular/common';
import { Component,ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TransactionService } from '../../core/services/transaction';

@Component({
  selector: 'app-transfer',
  imports: [FormsModule,CommonModule],
  templateUrl: './transfer.html',
  styleUrl: './transfer.css',
})
export class Transfer {
  transferData = {
    toAccountNumber: '',
    amount: '',
    description: ''
  };

  successMessage = '';
  errorMessage = '';

  constructor(private transactionService: TransactionService,
              private cdr :ChangeDetectorRef
  ) {}

  transfer(): void {
  this.successMessage = '';
  this.errorMessage = '';

  const payload = {
    toAccountNumber: this.transferData.toAccountNumber,
    amount: Number(this.transferData.amount),
    description: this.transferData.description
  };

  this.transactionService.transfer(payload).subscribe({
    next: (res) => {
      this.successMessage = res.message || 'Money transferred successfully';

      this.transferData = {
        toAccountNumber: '',
        amount: '',
        description: ''
      };

      this.cdr.detectChanges();
    },

    error: (err) => {
      console.log('TRANSFER ERROR:', err.error);

      this.errorMessage =
        err.error?.message ||
        err.error?.error ||
        'Transfer failed. Check account number or balance.';

      this.cdr.detectChanges();
    }
  });
}
}
