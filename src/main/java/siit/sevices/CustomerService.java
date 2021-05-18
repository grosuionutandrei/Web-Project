package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.ValidationException;
import siit.db.CustomerDao;
import siit.db.OrderDao;
import siit.db.OrderProductDao;
import siit.model.Customer;
import siit.model.Order;
import siit.model.OrderProduct;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderProductDao orderProductDao;

    public List<Customer> getCustomers() {
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerById(int id) {
        Customer customer = customerDao.getCustomerById(id);
        List<Order> orders = orderDao.getOrdersBy(id);

        for(Order or:orders){
            double value=0;
            for(OrderProduct op:orderProductDao.getOrderProductBy(or.getId())){
                BigDecimal valueTemp = op.getQuantity().multiply(op.getProduct().getPrice());
                value += valueTemp.doubleValue();
            }
            or.setValue(value);
            System.out.println(or);
        }
        customer.setOrders(orders);
        return customer;
    }

    public void updateCustomer(Customer customer) {
        if (customer.getPhone() != null && customer.getPhone().matches("\\+?\\d+")) {
            customerDao.updateCustomer(customer);
        } else {
            throw new ValidationException("Invalid phone number");
        }
    }


}
