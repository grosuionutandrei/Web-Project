package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.ProductDao;
import siit.model.OrderProduct;
import siit.model.Product;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> getProductsBy(String term){
        return productDao.getProductsBy(term);
    }
    public Product getProduct(OrderProduct orderProduct){
        return productDao.getProductById( orderProduct);
    }
    public String getImage(int productId,OrderProduct orderProduct){
        Product prod = productDao.getProductById(orderProduct);
        return prod.getUrl();
    }
}
