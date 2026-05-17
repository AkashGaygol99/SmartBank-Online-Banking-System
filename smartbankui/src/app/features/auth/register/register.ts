import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [FormsModule,CommonModule,RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
registerData = {
    name: '',
    email: '',
    password: ''
  };

  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  register(): void {

  this.errorMessage = '';

  this.authService.register(this.registerData).subscribe({

    next: () => {

      alert('Registration successful! Please login.');

      this.router.navigate(['/login']);
    },

    error: (err) => {

      console.log(err.error);

      this.errorMessage =
        err.error?.message ||
        'Registration failed. Email may already exist.';
    }
  });
}
}
