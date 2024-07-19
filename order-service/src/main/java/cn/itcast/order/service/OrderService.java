package cn.itcast.order.service;


import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import com.wang.feign.clients.UserClient;
import com.wang.feign.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private final OrderMapper orderMapper;

    private final RestTemplate restTemplate;

    private final UserClient userClient;

    public OrderService(OrderMapper orderMapper, RestTemplate restTemplate, UserClient userClient) {
        this.orderMapper = orderMapper;
        this.restTemplate = restTemplate;
        this.userClient = userClient;
    }

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        User user = userClient.getUserById(order.getUserId());
        order.setUser(user);
    /*    //2
        String url="http://user-service/user/"+order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
*/
        // 4.返回
        return order;
    }
}
