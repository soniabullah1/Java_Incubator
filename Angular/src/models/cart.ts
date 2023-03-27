import { CartItems } from "./cartItems";

export interface Cart {
    cartID: number;
    customerID?: number;

    cartItems?: CartItems[];

}