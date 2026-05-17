import { CommonModule } from '@angular/common';
import { Component, OnInit,ChangeDetectorRef } from '@angular/core';
import { AdminService } from '../../../core/services/admin';

@Component({
  selector: 'app-users',
  imports: [CommonModule],
  templateUrl: './users.html',
  styleUrl: './users.css',
})
export class Users implements OnInit{
  users: any[] = [];
  errorMessage = '';

  constructor(private adminService: AdminService,private cdr:ChangeDetectorRef) {}

  ngOnInit(): void {
    this.adminService.users().subscribe({
      next: (res) => {this.users = res; this.cdr.detectChanges();},
      error: () => this.errorMessage = 'Unable to load users'
    });
  }
}
