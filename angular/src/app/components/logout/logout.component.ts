import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private _userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this._userService.logOut();
    this.router.navigate(['main']);
  }

}
