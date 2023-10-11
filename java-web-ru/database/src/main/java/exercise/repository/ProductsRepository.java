package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (title, price) VALUES (?, ?)";
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entry");
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (var connection = dataSource.getConnection();
             var stmt = connection.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");
                Product product = new Product(title, price);
                product.setId(id);
                return Optional.of(product);
            }
            return Optional.empty();
        }
    }

    public static List<Product> getEntities() throws SQLException {
        String sql = "SELECT * FROM products";
        try (var conn = dataSource.getConnection();
        var stmt = conn.prepareStatement(sql)
        ) {
            var resultSet = stmt.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");
                var id = resultSet.getLong("id");
                Product product = new Product(title, price);
                product.setId(id);
                products.add(product);
            }
            return products;
        }
    }

    // END
}
