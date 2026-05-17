import { Component, OnInit,ChangeDetectorRef } from '@angular/core';
import { AccountService } from '../../core/services/account';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-account',
  imports: [CommonModule],
  templateUrl: './account.html',
  styleUrl: './account.css',
})
export class Account implements OnInit{
 account: any;
  errorMessage = '';

  constructor(private accountService: AccountService,private cdr:ChangeDetectorRef) {}

  ngOnInit(): void {
    this.accountService.getAccount().subscribe({
      next: (res) => {this.account = res; this.cdr.detectChanges();},
      error: () => this.errorMessage = 'Unable to load account details'
    });
  }
}
