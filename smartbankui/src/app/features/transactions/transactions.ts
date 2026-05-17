import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit , ChangeDetectorRef} from '@angular/core';
import { TransactionService } from '../../core/services/transaction';

@Component({
  selector: 'app-transactions',
  imports: [CommonModule,DatePipe],
  templateUrl: './transactions.html',
  styleUrl: './transactions.css',
})
export class Transactions implements OnInit{
  transactions: any[] = [];
  errorMessage = '';

  constructor(private transactionService: TransactionService,private cdr:ChangeDetectorRef) {}

  ngOnInit(): void {
  this.transactionService.history().subscribe({
    next: (res) => {
      console.log('TRANSACTIONS RESPONSE:', res);
      this.transactions = res;
      this.cdr.detectChanges();
    },
    error: (err) => {
      console.log('TRANSACTIONS ERROR:', err);
      this.errorMessage = 'Unable to load transactions';
    }
  });
} 
}
