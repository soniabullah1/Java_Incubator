import { Books } from "./books";

export interface CartItems {
    cartItemID: number;
    cartID?: number;
    quantity?: number;

    bookID?: Books
  }