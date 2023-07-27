import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../Model/product';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {}
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('http://localhost:8082/api/products');
  }

  addProduct(product:Product):Observable<Product>{
return this.http.post<Product>('http://localhost:8080/api/products',product)

  }
}
