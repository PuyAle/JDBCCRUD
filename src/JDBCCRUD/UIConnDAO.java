package JDBCCRUD;

import static JDBCCRUD.DAOProductJDBCConn.sc;

//Class that connects the UI and the class DatabaseConnection wich is the class thats connects to the database. 
public class UIConnDAO {

    UI menu1 = new UI();
    DAOProductJDBCConn dbcon = new DAOProductJDBCConn();
    private static String name;

    public void connect() {
        boolean loop = true;
        while (loop) {
            int i = menu1.menu();
            {
                switch (i) {
                    case 1:
                        create();
                        break;
                    case 2:
                        dbcon.printList(dbcon.getAll());
                        break;
                    case 3:
                        getByName();
                        break;
                    case 4:
                        getByPriceRange();
                        break;
                    case 5:
                        update();
                        break;
                    case 6:
                        delete();
                        break;
                    case 0:
                        loop = false;
                    default:
                        System.out.println("Wrong choice");

                }
            }
        }

    }

    private void create() {
        System.out.print("Insert product name: ");
        name = sc.nextLine();
        System.out.print("Insert product price: ");
        
        double price = sc.nextDouble();
        sc.nextLine();
        
        dbcon.create(name, price);
    }

    private void getByName() {
        System.out.println("Insert name");
        name = sc.nextLine();
        dbcon.printList(dbcon.getByName(name));
    }

    private void getByPriceRange() {
        System.out.println("Insert minimun price: ");
        double price1 = sc.nextDouble();
        System.out.println("Insert maximum price: ");
        double price2 = sc.nextDouble();
        sc.nextLine();
        
        dbcon.printList(dbcon.getByPriceRange(price1, price2));
    }

    private void delete() {
        System.out.println("Enter the id of the product you want to delete: ");
        int id1 = sc.nextInt();
        if (dbcon.remove(id1)) {
            System.out.println("Product removed!");
        } else {
            System.out.println("Product could not be removed");
        }
    }

    private void update() {
        System.out.println("Insert id of product you wish to update: ");
        int id = sc.nextInt();
        System.out.println("Enter new price: ");
        double price3 = sc.nextDouble();
        sc.nextLine();
        if (dbcon.updatePrice(id, price3)) {
            System.out.println("The price is now updated");
        } else {
            System.out.println("The price could not update, try again");
        }
    }

}
