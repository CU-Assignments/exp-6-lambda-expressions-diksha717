import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

class Product {
    String name, category;
    double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class ProductProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter Product Name: ");
            String name = sc.next();
            System.out.print("Enter Category: ");
            String category = sc.next();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();

            products.add(new Product(name, category, price));
        }

        // **Grouping products by category**
        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));

        // **Finding the most expensive product in each category**
        Map<String, Product> mostExpensiveByCategory = products.stream()
            .collect(Collectors.toMap(
                p -> p.category,
                Function.identity(),
                (p1, p2) -> p1.price > p2.price ? p1 : p2
            ));

        // **Calculating the average price of all products**
        double avgPrice = products.stream()
            .mapToDouble(p -> p.price)
            .average()
            .orElse(0.0);

        // **Displaying the results**
        System.out.println("\nProducts Grouped by Category:");
        groupedByCategory.forEach((category, productList) -> 
            System.out.println(category + " -> " + productList));

        System.out.println("\nMost Expensive Product in Each Category:");
        mostExpensiveByCategory.forEach((category, product) -> 
            System.out.println(category + " -> " + product));

        System.out.println("\nAverage Price of All Products: $" + avgPrice);

        sc.close();
    }
}
