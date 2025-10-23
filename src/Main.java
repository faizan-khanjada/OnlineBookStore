import java.util.*;

class Cart {
    CartItem[] cartItems;
    int itemCount;

    Cart(int maxItems) {
        cartItems = new CartItem[maxItems];
        itemCount = 0;
    }

    // Method to add book to the cart with quantity
    void addToCart(Book book, int quantity) {
        for (int i = 0; i < itemCount; i++) {
            if (cartItems[i].book.title.equalsIgnoreCase(book.title)) {
                cartItems[i].quantity += quantity;
                System.out.println(quantity + " copies of " + book.title + " added to the cart.");
                return;
            }
        }

        if (itemCount < cartItems.length) {
            cartItems[itemCount] = new CartItem(book, quantity);
            itemCount++;
            System.out.println(quantity + " copies of " + book.title + " added to the cart.");
        } else {
            System.out.println("Cart is full. Cannot add more items.");
        }
    }

    // Method to remove book from the cart based on quantity
    void removeFromCart(String title, int quantity) {
        for (int i = 0; i < itemCount; i++) {
            if (cartItems[i].book.title.equalsIgnoreCase(title)) {
                if (cartItems[i].quantity > quantity) {
                    cartItems[i].quantity -= quantity;
                    System.out.println(quantity + " copies of " + title + " removed from the cart.");
                } else {
                    for (int j = i; j < itemCount - 1; j++) {
                        cartItems[j] = cartItems[j + 1];
                    }
                    cartItems[itemCount - 1] = null;
                    itemCount--;
                    System.out.println(title + " removed from the cart.");
                }
                return;
            }
        }
        System.out.println("Book not found in the cart.");
    }

    // Method to display all items in the cart
    void displayCart() {
        if (itemCount == 0) {
            System.out.println("Your cart is empty!");
        } else {
            System.out.println("Items in your cart:");
            for (int i = 0; i < itemCount; i++) {
                cartItems[i].displayCartItem();
                System.out.println("----------------------------------");
            }
        }
    }

    // Method to calculate total price of items in the cart
    double calculateTotal() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += cartItems[i].book.getPrice() * cartItems[i].quantity;
        }
        return total;
    }

    // Method to calculate total number of books in the cart
    int getTotalBookCount() {
        int totalBooks = 0;
        for (int i = 0; i < itemCount; i++) {
            totalBooks += cartItems[i].quantity;
        }
        return totalBooks;
    }

    class CartItem {
        Book book;
        int quantity;

        CartItem(Book book, int quantity) {
            this.book = book;
            this.quantity = quantity;
        }

        void displayCartItem() {
            System.out.println("Title: " + book.title + ", Quantity: " + quantity + ", Price: $" + book.getPrice() + ", Total: $" + (book.getPrice() * quantity));
        }
    }
}

class Book {
    String title;
    String author;
    double price;
    String category;
    double rating;

    Book(String title, String author, double price, String category) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
        this.rating = 0;
    }

    // Method to display book details
    void displayBook() {
        System.out.println("Title: " + title + ", Author: " + author + ", Price: $" + price + ", Category: " + category + ", Rating: " + rating + " stars");
    }

    // Method to get price of the book
    double getPrice() {
        return price;
    }

    // Method to set book rating
    void setRating(double rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        } else {
            System.out.println("Invalid rating. Rating should be between 1 and 5.");
        }
    }
}

class OnlineBookStore {
    String[][] bookDetails = new String[8][4];
    Book[] books = new Book[8];
    Scanner sc = new Scanner(System.in);

    OnlineBookStore() {
        bookDetails[0] = new String[]{"The Great Gatsby", "F. Scott Fitzgerald", "10.99", "Fiction"};
        bookDetails[1] = new String[]{"1984", "George Orwell", "15.99", "Fiction"};
        bookDetails[2] = new String[]{"Moby Dick", "Herman Melville", "8.99", "Fiction"};
        bookDetails[3] = new String[]{"The Origin of Species", "Charles Darwin", "12.49", "Science"};
        bookDetails[4] = new String[]{"A Brief History of Time", "Stephen Hawking", "18.99", "Science"};
        bookDetails[5] = new String[]{"Sapiens", "Yuval Noah Harari", "20.99", "Non-Fiction"};
        bookDetails[6] = new String[]{"Educated", "Tara Westover", "14.99", "Non-Fiction"};
        bookDetails[7] = new String[]{"The Silent Patient", "Alex Michaelides", "13.99", "Thriller"};

        for (int i = 0; i < bookDetails.length; i++) {
            books[i] = new Book(bookDetails[i][0], bookDetails[i][1], Double.parseDouble(bookDetails[i][2]), bookDetails[i][3]);
        }
    }

    // Method to display all available books
    void displayBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            book.displayBook();
            System.out.println("----------------------------------");
        }
    }

    // Method to search books by title or author
    void searchBooks() {
        System.out.print("Enter book title or author to search: ");
        String searchQuery = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Book book : books) {
            if (book.title.equalsIgnoreCase(searchQuery) || book.author.equalsIgnoreCase(searchQuery)) {
                System.out.println("Title: " + book.title + ", Author: " + book.author + ", Price: $" + book.price + ", Category: " + book.category);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found matching your search.");
        }
    }

    // Method to sort books by title
    void sortBooksByTitle() {
        for (int i = 0; i < books.length - 1; i++) {
            for (int j = i + 1; j < books.length; j++) {
                if (books[i].title.compareToIgnoreCase(books[j].title) > 0) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                }
            }
        }
        System.out.println("Books sorted by title.");
        displayBooks();
    }

    // Method to sort books by price
    void sortBooksByPrice() {
        for (int i = 0; i < books.length - 1; i++) {
            for (int j = i + 1; j < books.length; j++) {
                if (books[i].price > books[j].price) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                }
            }
        }
        System.out.println("Books sorted by price.");
        displayBooks();
    }

    // Method to filter books by category
    void filterBooksByCategory() {
        System.out.print("Enter the category to filter books (e.g., Fiction, Science, Non-Fiction, Thriller): ");
        String category = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Book book : books) {
            if (book.category.toLowerCase().equals(category)) {
                book.displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found in this category.");
        }
    }

    // Method to rate a book
    void rateBook() {
        System.out.print("Enter the title of the book you want to rate: ");
        String title = sc.nextLine();

        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                System.out.print("Enter rating (1 to 5 stars): ");
                double rating = sc.nextDouble();
                sc.nextLine();
                book.setRating(rating);
                System.out.println("Thank you for rating the book!");
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OnlineBookStore store = new OnlineBookStore();
        Cart cart = new Cart(10);

        String username = "user";
        String password = "password";
        int attempts = 0;

        while (attempts < 3) {
            System.out.print("Enter username: ");
            String inputUsername = sc.nextLine();
            System.out.print("Enter password: ");
            String inputPassword = sc.nextLine();

            if (inputUsername.equals(username) && inputPassword.equals(password)) {
                System.out.println("Login successful!\n");
                break;
            } else {
                attempts++;
                System.out.println("Invalid credentials. Try again.");
            }
        }

        if (attempts == 3) {
            System.out.println("Too many failed login attempts. Exiting...");
            return;
        }

        while (true) {
            System.out.println("\nWelcome to the Online Book Store!");
            System.out.println("1. View Available Books");
            System.out.println("2. Add Book to Cart");
            System.out.println("3. Remove Book from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Search Books");
            System.out.println("7. Sort Books by Title");
            System.out.println("8. Sort Books by Price");
            System.out.println("9. Filter Books by Category");
            System.out.println("10. Rate a Book");
            System.out.println("11. Exit");
            System.out.print("Please choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    store.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter the title of the book to add to the cart: ");
                    String titleToAdd = sc.nextLine();
                    System.out.print("Enter the quantity: ");
                    int quantityToAdd = sc.nextInt();
                    sc.nextLine();
                    boolean found = false;
                    for (Book book : store.books) {
                        if (book.title.equalsIgnoreCase(titleToAdd)) {
                            cart.addToCart(book, quantityToAdd);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of the book to remove from the cart: ");
                    String titleToRemove = sc.nextLine();
                    System.out.print("Enter the quantity to remove: ");
                    int quantityToRemove = sc.nextInt();
                    sc.nextLine();
                    cart.removeFromCart(titleToRemove, quantityToRemove);
                    break;
                case 4:
                    cart.displayCart();
                    break;
                case 5:
                    double total = cart.calculateTotal();
                    int totalBookCount = cart.getTotalBookCount();
                    System.out.println("Total Books: " + totalBookCount);
                    System.out.println("Total Price: $" + total);

                    if (totalBookCount > 3) {
                        double discount = total * 0.1;
                        total -= discount;
                        System.out.println("You received a 10% discount for purchasing more than 3 books: -$" + discount);
                    }

                    System.out.println("Final Total Price: $" + total);
                    System.out.println("Thank you for shopping with us!");
                    cart = new Cart(10);
                    break;
                case 6:
                    store.searchBooks();
                    break;
                case 7:
                    store.sortBooksByTitle();
                    break;
                case 8:
                    store.sortBooksByPrice();
                    break;
                case 9:
                    store.filterBooksByCategory();
                    break;
                case 10:
                    store.rateBook();
                    break;
                case 11:
                    System.out.println("Thank you for visiting the Online Book Store. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid Choice. Kindly enter a number between (1-11)");
            }
        }
    }
}