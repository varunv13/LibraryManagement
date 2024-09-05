import java.util.ArrayList;
import java.util.List;
enum TransactionType{
    RETURN, BORROW
}

class Transactions{
    // user
    private User user;
    // book
    private Book book;
    // what he's doing with the book
    private TransactionType type;

    public Transactions(User user, Book book, TransactionType type){
        this.user = user;
        this.book = book;
        this.type = type;
    }

    public User getUser(){
        return user;
    }

    public Book getBook(){
        return book;
    }

    public TransactionType getType(){
        return type;
    }

}

class User{
    // name
    private String userName;
    // id
    private String userId;

    // refering to the current object
    public User(String userName, String userId){
        this.userName = userName;
        this.userId = userId;
    }

    // getter()

    public String getUserName(){
        return userName;
    }

    public String getUserId(){
        return userId;
    }
}


class Book{
    // isbn 
    private String ISBN;
    // title
    private String title;
    // author
    private String author;
    // books available
    private int availableCopies;

    // refering to the current object
    public Book(String title, String author, String ISBN, int availableCopies){
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.availableCopies = availableCopies;
    }

    // getter() and setter() for accessing the private data members

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getISBN(){
        return ISBN;
    }

    public int getAvailableCopies(){
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies){
        this.availableCopies = availableCopies;
    }

}


class Library{
    // collections of books
    private List<Book> books;
    // collections / group of users (student)
    private List<User> users;
    // transactions (students either borrowing or returning books)
    private List<Transactions> transactions;

    Library(){
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addBook(Book bookAdded){
        books.add(bookAdded);
    }

    public void addUser(User userBecameMember){
        users.add(userBecameMember);
    }
    
    
    public void performTransactions(User user, Book books, TransactionType type){
        //user is borrowing
        if(type == TransactionType.BORROW){
            // check whether there are books available for lending
            if(books.getAvailableCopies() > 0){
                books.setAvailableCopies(books.getAvailableCopies() - 1);
                System.out.println(user.getUserName() + " borrowed " + books.getTitle());
                transactions.add(new Transactions(user, books, TransactionType.BORROW));
            }
            else{
                // if not then display regret message
                System.out.println("Sorry," + user.getUserName() + " the book " + books.getTitle() +" is not available for lending at the moment");
            }
        }
        else{
            // user is returning the book
            books.setAvailableCopies(books.getAvailableCopies() + 1);
            System.out.println(user.getUserName() + " returned the book " + books.getTitle());
        }
    }

    public void displayTransactions(){
        System.out.println("Transactions are :");
        for (Transactions traverse : transactions) {
            System.out.println(traverse.getUser().getUserName() + " " + (traverse.getType() == TransactionType.BORROW ? "borrowed" : "returned" )+ " " + traverse.getBook().getTitle());
        }
    }

}


public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("One Piece", "Echiro Oda", "ROGERS100", 100000);
        library.addBook(book1);

        Book book2 = new Book("Naruto", "Kishimoto", "GhostOfUchiha", 1);
        library.addBook(book2);

        User user1 = new User("Varun", "13042003");
        library.addUser(user1);
        User user2 = new User("Jay", "15082003");
        library.addUser(user2);


        library.performTransactions(user1, book1, TransactionType.RETURN);
        library.performTransactions(user1, book2, TransactionType.BORROW);
        library.performTransactions(user2, book2, TransactionType.BORROW);

        library.displayTransactions();
        
    }
}
