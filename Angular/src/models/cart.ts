import { CartItems } from "./cartItems";

export interface Cart {
    cartID: number;
    userID?: number;

    cartItems: CartItems[];

}