import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoanService {

  private baseUrl = `${environment.apiUrl}/loans`;

  constructor(private http: HttpClient) {}

  applyLoan(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, data);
  }

  myLoans(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}