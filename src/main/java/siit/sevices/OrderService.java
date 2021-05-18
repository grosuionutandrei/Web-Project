package siit.sevices;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.ValidationException;
import siit.db.OrderDao;
import siit.db.OrderProductDao;
import siit.db.ProductDao;
import siit.model.Order;
import siit.model.OrderProduct;
import siit.model.Product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderProductDao orderProductDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    ProductDao productDao;

    public void deleteOrderBy(int orderId) {
        orderDao.deleteOrderProduct(orderId);
        orderDao.deleteOrderBy(orderId);
    }


    public List<OrderProduct> getOrderProductBy(int customerId, int orderId) {
        return orderProductDao.getOrderProductBy(orderId);
    }

    public List<Order> getOrders(String number,String placed, int customerId){
        Order order = new Order();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(placed, formatter);
        order.setPlaced(dateTime);
        if(number.matches("FNR98\\w\\d{3}")){
            order.setNumber(number);
        orderDao.insert(order,customerId);}
        else{
           throw new ValidationException("Numarul nu este corect");
        }

        List <Order> orders =orderDao.getOrdersBy(customerId);
        return orders;
    }





    public Order getOrderBy(int customerId, int orderId) {
        for (Order order : orderDao.getOrdersBy(customerId)) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    private OrderProduct getOrderProduct2(List<OrderProduct>intermediateOpList, OrderProduct orderProduct,int orderId){

        for (OrderProduct op: intermediateOpList){
            if(op.getProduct().getId()==orderProduct.getProduct().getId()){
            return op;
            }
        }
       return null;
    }
    private OrderProduct prepareForInsertion(OrderProduct orderProduct,int orderId){
        OrderProduct orderToInsert = new OrderProduct();
        Product prod = productDao.getProductById(orderProduct);
        BigDecimal value = orderProduct.getQuantity().multiply(prod.getPrice());
        orderToInsert.setId(orderId);
        orderToInsert.setQuantity(orderProduct.getQuantity());
        orderToInsert.setValue(value);
        orderToInsert.setProduct(prod);
        return orderToInsert;
    }

    public OrderProduct getOrderProduct(OrderProduct orderProduct, int customerId,int orderId){
        List<OrderProduct> workingList= orderProductDao.getOrderProductBy(orderId);

        OrderProduct orderProductModificat = this.getOrderProduct2(workingList,orderProduct,orderId);

        if(workingList.contains(orderProductModificat)){
            for (OrderProduct op :workingList){
                if(op.getProduct().getId()==orderProduct.getProduct().getId()){
                    BigDecimal quantity = op.getQuantity().add(orderProduct.getQuantity());
                    op.setQuantity(quantity);
                    orderProductDao.update(op,orderId);
                    return op;
                }
            }
        }else {
            orderProductModificat= this.prepareForInsertion(orderProduct,orderId);
            orderProductDao.insertOrder(orderProductModificat,orderId);
            workingList.add(orderProductModificat);
            return orderProductModificat;
        }
        return null;
    }

   public void delete(int orderProductId,int customerId,int orderId){
        orderProductDao.delete(orderProductId);
               }

   public BigDecimal getValue(int customerId) {
       BigDecimal sum = BigDecimal.valueOf(0);
       for (Order ord : orderDao.getOrdersBy(customerId)) {
           for (OrderProduct op : orderProductDao.getOrderProductBy(ord.getId())) {
               if (op.getId() == ord.getId()) {
                   BigDecimal value = op.getQuantity().multiply(op.getProduct().getPrice());
                   sum = sum.add(value);
               }
           }

           return sum;

       }

       return  null;
   }














}
