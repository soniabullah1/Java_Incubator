import { Books } from "./books";

export interface CartItems {
    cartItemID: number;
    cartID?: number;
    quantity?: number;

    bookID: number;
    title?: string;
    author?: string;
    dateOfPublication?: Date;
    version?: number;
    numberOfBooksInStock?: number;
    price?: number;
    
    books?: Books;
  }