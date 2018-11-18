package JDBCCRUD;

public class UI {
    
    
    private static int choice;
    
    //Method that prints the menu and returns the users choice. 
    public int menu() {
        System.out.println("==================================");
        System.out.println("1. Register product");
        System.out.println("2. Print all products");
        System.out.println("3. Print product by name");
        System.out.println("4. Print product by pricerange");
        System.out.println("5. Update product");
        System.out.println("6. Delete product");
        System.out.println("0. Exit");
        System.out.println("==================================");

        boolean loop = true;
        while (loop) {
            try {

                System.out.println("Enter a number from the menu");
                choice = DAOProductJDBCConn.sc.nextInt();
                DAOProductJDBCConn.sc.nextLine();
                loop = false;

            } catch (Exception e) {
                DAOProductJDBCConn.sc.nextLine();
                System.out.println("wrong choice try again");
            }

        }

        return choice;
    }

}
