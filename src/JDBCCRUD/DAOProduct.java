
package JDBCCRUD;
import java.util.List;

public interface DAOProduct {
  

void create(String name, double price);
// creates a new product (ignores p.id but autonumbers the database id)

List<Product> getByName(String name);
// returns all product with the exact name

List<Product> getByPriceRange(double min, double max);
// returns all products within the price 

List<Product> getAll();
//returns all products

boolean updatePrice(int id, double newPrice);
// set a new price for the product, return success/failure

boolean remove(int id);
// removes the product, return success/failure
    
}
