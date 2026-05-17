import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
   private baseUrl = `${environment.apiUrl}/admin`;

  constructor(private http: HttpClient) {}

  dashboard(): Observable<any> {
    return this.http.get(`${this.baseUrl}/dashboard`);
  }

  users(): Observable<any> {
    return this.http.get(`${this.baseUrl}/users`);
  }

  loans(): Observable<any> {
    return this.http.get(`${this.baseUrl}/loans`);
  }

 approveLoan(id: number): Observable<any> {
  return this.http.put(`${this.baseUrl}/loans/${id}/APPROVED`, {});
}

rejectLoan(id: number): Observable<any> {
  return this.http.put(`${this.baseUrl}/loans/${id}/REJECTED`, {});
}
accounts(): Observable<any> {
  return this.http.get(`${this.baseUrl}/accounts`);
}

freezeAccount(id: number, value: boolean): Observable<any> {
  return this.http.put(
    `${this.baseUrl}/accounts/${id}/freeze?value=${value}`,
    {}
  );
}
}
