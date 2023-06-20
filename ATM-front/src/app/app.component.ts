import { Component, OnInit } from '@angular/core';
import  { AccountService } from './account.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
  title = 'ATM';

  accounts: any;

  accountDep: string;
  amountDep: number;

  accountWit: string;
  amountWit: number;

  source: string;
  dest: string;
  transferVal: number;

  constructor(private accountService: AccountService) {
    this.accountDep = '';
    this.amountDep = 0;
    this.accountWit = '';
    this.amountWit = 0;

    this.source = '';
    this.dest = '';
    this.transferVal = 0;
  }

  ngOnInit() {
    this.accountService.getAccounts()
      .subscribe( result =>  this.accounts = result)
  }

  deposit() {
    this.accountService.deposit(this.accountDep, this.amountDep);
    window.location.reload();
  }

  withdrawal() {
    this.accountService.withdrawal(this.accountWit, this.amountWit);
    window.location.reload();
  }

  transfer() {
    this.accountService.transfer(this.source, this.dest, this.transferVal);
    window.location.reload();
  }

}
