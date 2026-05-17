import { Routes } from '@angular/router';

import { Login } from './features/auth/login/login';
import { Dashboard } from './features/dashboard/dashboard';
import { Account } from './features/account/account';
import { Transfer } from './features/transfer/transfer';
import { Transactions } from './features/transactions/transactions';
import { Loan } from './features/loan/loan';
import { AdminDashboard } from './features/admin/admin-dashboard/admin-dashboard';
import { Users } from './features/admin/users/users';
import { Register } from './features/auth/register/register';
import { authGuard } from './core/guards/auth-guard';
import { adminGuard } from './core/guards/admin-guard';

export const routes: Routes = [

  { path: '', redirectTo: 'login', pathMatch: 'full' },

  { path: 'login', component: Login },

  { path: 'register', component: Register },

  { path: 'dashboard', component: Dashboard,canActivate: [authGuard] },

  { path: 'account', component: Account,canActivate: [authGuard] },

  { path: 'transfer', component: Transfer,canActivate: [authGuard] },

  { path: 'transactions', component: Transactions,canActivate: [authGuard] },

  { path: 'loan', component: Loan,canActivate: [authGuard] },

  { path: 'admin', component: AdminDashboard,canActivate: [authGuard,adminGuard] },

  { path: 'admin/users', component: Users,canActivate: [authGuard,adminGuard] },

  { path: '**', redirectTo: 'login' }

];