import { Component, OnInit } from '@angular/core';
import { CartItem } from 'src/app/Model/cart';

import { CartService } from 'src/app/Services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: CartItem[] = [];
  total: number = 0;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.loadCartItems();
    this.calculateTotal();
  }

  loadCartItems(): void {
    this.cartService.getCartItems().subscribe(
      cartItems => {
        this.cartItems = cartItems;
        this.calculateTotal();
      },
      error => {
        console.error('Failed to retrieve cart items', error);
      }
    );
    this.cartService.getTotalPrice().subscribe(
      totalPrice => {
        this.total = totalPrice;
      },
      error => {
        console.error('Failed to retrieve total price', error);
      }
    );
  }

  removeFromCart(productId: number) {
    this.cartService.removeFromCart(productId).subscribe(
      () => {
        this.cartItems = this.cartItems.filter(item => item.id !== productId);
        this.calculateTotal();
        
      },
      error => {
        console.error('Failed to remove item from cart', error);
      }
    );
    window.location.reload();
  } 

  private calculateTotal(): void {
    this.total = this.cartItems.reduce(
      (total, item) => total + item.price * item.quantity,
      0
    );
  }
}
