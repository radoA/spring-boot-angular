import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  public get(id: number): Observable<any> {
    return this.http.get(`${this.usersUrl}/${id}`);
  }

  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }

  public update(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.usersUrl}/${id}`, value);
  }

  public delete(id: number): Observable<any> {
    return this.http.delete(`${this.usersUrl}/${id}`, { responseType: 'text' });
  }


}
