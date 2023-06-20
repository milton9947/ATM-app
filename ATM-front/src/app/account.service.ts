import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  constructor(private http: HttpClient) { }

  getAccounts() {
    return this.http.get("http://localhost:8080/atm-ws/account/v1");
  }

  deposit(accountNameVal: string, amountVal: number) {
    this.http.post<any>('http://localhost:8080/atm-ws/atm/v1/deposit', { amount: amountVal, accountName: accountNameVal })
    .subscribe(data => {
    });
  }

  withdrawal(accountNameVal: string, amountVal: number) {
    this.http.post<any>('http://localhost:8080/atm-ws/atm/v1/withdrawal', { amount: amountVal, accountName: accountNameVal })
    .subscribe(data => {
    });
  }

  transfer(sourceVal: string, destVal: string, amountVal: number) {
    this.http.post<any>('http://localhost:8080/atm-ws/atm/v1/transfer', 
      { accountNameSource: sourceVal, accountNameDestination: destVal, amount: amountVal })
      .subscribe(data => {
    });
  }
}
