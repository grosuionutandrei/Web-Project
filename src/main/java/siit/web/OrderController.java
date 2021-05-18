package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.model.Order;
import siit.model.OrderProduct;
import siit.model.Product;
import siit.sevices.OrderService;
import siit.sevices.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/customers/{customerId}/orders/{orderId}")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;



    @GetMapping
    public Order getOrderBy(@PathVariable int customerId, @PathVariable int orderId) {
        return orderService.getOrderBy(customerId, orderId);

    }

    @GetMapping("/products")
    public List<OrderProduct> getOrderProducts(@PathVariable int customerId, @PathVariable int orderId) {
        return orderService.getOrderProductBy(customerId, orderId);

    }

    @PostMapping("/products")
    public OrderProduct addProduct(@RequestBody OrderProduct orderProduct, @PathVariable int customerId, @PathVariable int orderId) {

        return  orderService.getOrderProduct(orderProduct,customerId,orderId);

    }

    @DeleteMapping("/products/{orderProductId}")
    public void delete(@PathVariable int orderProductId ,@PathVariable int customerId,@PathVariable int orderId){
    orderService.delete(orderProductId,customerId,orderId);
    }
//
//    @GetMapping("/products/")
//    public String getImage(@RequestBody OrderProduct orderProduct,@PathVariable int productId,@PathVariable int customerId,@PathVariable int orderId){
//        return productService.getImage(productId,orderProduct) ;
//    }


}
