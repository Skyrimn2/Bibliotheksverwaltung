package application;

import domain.Book;
import domain.BookCopy;
import domain.Employee;
import domain.Lending;
import domain.User;
import domain.UserInterface;

public class MenuConfigurator {

    private final String dbPath;
    private final FrontendHandler frontend;
    private final DBHandler<Book> bookDB;
    private final DBHandler<BookCopy> bookCopyDB;
    private final DBHandler<Lending> lendingDB;
    private final DBHandler<User> userDB;
    private final IDisplayableFactory displayableFactory;

    public MenuConfigurator(String dbPath, FrontendHandler frontend,
                            DBHandler<Book> bookDB, DBHandler<BookCopy> bookCopyDB,
                            DBHandler<Lending> lendingDB, DBHandler<User> userDB, IDisplayableFactory displayableFactory) {
        this.dbPath = dbPath;
        this.frontend = frontend;
        this.bookDB = bookDB;
        this.bookCopyDB = bookCopyDB;
        this.lendingDB = lendingDB;
        this.userDB = userDB;
        this.displayableFactory = displayableFactory;
    }

    public Menu configureMenu() {
        Menu menu = new Menu();
        UserInterface user = frontend.getUser();

        if (user instanceof Employee) {
            addEmployeeActions(menu);
        } else if (user instanceof User) {
            addUserActions(menu);
        }

        menu.registerAction(new QuitAppAction());
        return menu;
    }

    protected void addEmployeeActions(Menu menu) {
        menu.registerAction(new ListBooksAction(bookDB, frontend, displayableFactory));
        menu.registerAction(new ListBookByTitleAction(bookDB, frontend, displayableFactory));
        menu.registerAction(new ListAllLendings(lendingDB, frontend, displayableFactory));
    }

    protected void addUserActions(Menu menu) {
        menu.registerAction(new ListBooksAction(bookDB, frontend, displayableFactory));
        menu.registerAction(new LendBookAction(bookDB, bookCopyDB, lendingDB, userDB, frontend));
        menu.registerAction(new ReturnLendingAction(lendingDB, bookCopyDB, frontend));
        menu.registerAction(new ListBookByTitleAction(bookDB, frontend, displayableFactory));
        menu.registerAction(new ListUserLendings(lendingDB, frontend, displayableFactory));
    }
}
