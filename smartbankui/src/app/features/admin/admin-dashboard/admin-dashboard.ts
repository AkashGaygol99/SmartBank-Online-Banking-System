import { CommonModule } from '@angular/common';
import { Component, OnInit ,ChangeDetectorRef} from '@angular/core';
import { AdminService } from '../../../core/services/admin';

@Component({
  selector: 'app-admin-dashboard',
  imports: [CommonModule],
  templateUrl: './admin-dashboard.html',
  styleUrl: './admin-dashboard.css',
})
export class AdminDashboard implements OnInit {
  stats: any = {};
  loans: any[] = [];
  accounts: any[] = [];
  errorMessage = '';

  constructor(private adminService: AdminService,private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadDashboard();
    this.loadLoans();
    this.loadAccounts();
  }

  loadDashboard(): void {
    this.adminService.dashboard().subscribe({
      next: (res) => {this.stats = res;this.cdr.detectChanges();},
      error: () => this.errorMessage = 'Unable to load admin dashboard'
    });
  }

  loadLoans(): void {
    this.adminService.loans().subscribe({
      next: (res) => {this.loans = res;this.cdr.detectChanges();},
      error: () => this.errorMessage = 'Unable to load loans'
    });
  }
  loadAccounts(): void {
  this.adminService.accounts().subscribe({
    next: (res) => {
      this.accounts = res;
      this.cdr.detectChanges();
    },
    error: () => {
      this.errorMessage = 'Unable to load accounts';
      this.cdr.detectChanges();
    }
  });
}

freeze(id: number): void {
  this.adminService.freezeAccount(id, true).subscribe({
    next: () => this.loadAccounts()
  });
}

unfreeze(id: number): void {
  this.adminService.freezeAccount(id, false).subscribe({
    next: () => this.loadAccounts()
  });
}

  approve(id: number): void {
    this.adminService.approveLoan(id).subscribe({
      next: () => this.loadLoans()
    });
  }

  reject(id: number): void {
    this.adminService.rejectLoan(id).subscribe({
      next: () => this.loadLoans()
    });
  }
}
