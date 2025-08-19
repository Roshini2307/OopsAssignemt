

class Book {
    String title;
    String author;
    double price;
  
    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
 
    void displayBook() 
    {
        System.out.println("Title  : " + title);
        System.out.println("Author : " + author);
        System.out.println("Price  : ₹" + price);
        System.out.println("  ");
    }
}

public class Assignment { 
    static void displayAllBooks(Book[] books) 
    {
        for (Book book : books) 
        {
            book.displayBook();
        }
    }

    public static void main(String[] args) 
    {   
        Book[] books = new Book[3];

        books[0] = new Book("The Alchemist", "Paulo Coelho", 499);
        books[1] = new Book("Wings of Fire", "A.P.J. Abdul Kalam", 350);
        books[2] = new Book("Java Programming", "Herbert Schildt", 799);
 
        displayAllBooks(books);
    }
}

