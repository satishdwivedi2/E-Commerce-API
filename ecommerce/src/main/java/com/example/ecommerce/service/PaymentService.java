
package com.example.ecommerce.service;

import com.example.ecommerce.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.dto.OrderUserDto;
import com.example.ecommerce.dto.PaymentResponseDto;
import com.example.ecommerce.dto.RazorpayStatusDto;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.OrderStatus;
import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.PaymentStatus;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;

import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.PaymentRepository;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final ProductRepository productRepository;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final RazorpayClient razorpayClient;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public PaymentResponseDto createPayment(int orderId) throws RazorpayException {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException(
                        "Order not found with id: " + orderId));

        Optional<Payment> existingPayment = paymentRepository.findByOrder(order);

        if (existingPayment.isPresent()) {
            return toPaymentResponseDto(existingPayment.get());
        }

        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setStatus(com.example.ecommerce.entity.PaymentStatus.PENDING);
        payment.setAmount(order.getTotalAmount());

        JSONObject options = new JSONObject();
        options.put("amount", order.getTotalAmount() * 100);
        options.put("currency", "INR");

        com.razorpay.Order razorpayOrder = razorpayClient.orders.create(options);

        String razorpayOrderId = razorpayOrder.get("id");

        payment.setRazorpayOrderId(razorpayOrderId);
        payment.setPaymentGateway("Razorpay");

        Payment p = paymentRepository.save(payment);

        return toPaymentResponseDto(p);
    }

    private PaymentResponseDto toPaymentResponseDto(Payment p) {

        PaymentResponseDto response = new PaymentResponseDto();

        response.setId(p.getId());
        response.setAmount(p.getAmount());
        response.setPaymentGateway(p.getPaymentGateway());
        response.setRazorpayOrderId(p.getRazorpayOrderId());
        response.setStatus(p.getStatus());
        response.setTransactionId(p.getTransactionId());

        response.setOrder(toOrderResponseDto(p.getOrder()));

        return response;
    }

    private OrderResponseDto toOrderResponseDto(Order order) {

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setTotalAmount(order.getTotalAmount());
        orderResponseDto.setOrderItems(order.getOrderItems());

        OrderUserDto userDto = new OrderUserDto();

        userDto.setId(order.getUser().getId());
        userDto.setName(order.getUser().getName());
        userDto.setEmail(order.getUser().getEmail());
        userDto.setPhoneNo(order.getUser().getPhoneNo());

        orderResponseDto.setUser(userDto);

        return orderResponseDto;
    }
     @Transactional 
    public PaymentResponseDto verifyPayment(RazorpayStatusDto razorpayStatusDto) throws RazorpayException {
        String rsd = razorpayStatusDto.getRazorpayOrderId();
        Optional<Payment> p = paymentRepository.findByRazorpayOrderId(rsd);
        if (!p.isPresent())
            throw new RuntimeException("Payment not Found with id " + rsd);

        Payment payment = p.get();
        String id = razorpayStatusDto.getRazorpayPaymentId();
        String sig = razorpayStatusDto.getRazorpaySignature();
        JSONObject opt = new JSONObject();
        opt.put("razorpay_order_id", rsd);
        opt.put("razorpay_payment_id", id);
        opt.put("razorpay_signature", sig);
        boolean verified = Utils.verifyPaymentSignature(opt, keySecret);
        if (!verified)
            throw new RuntimeException("Payment Verification Failed");
        payment.setStatus(PaymentStatus.CONFIRMED);
        payment.setTransactionId(id);
        Order order = payment.getOrder();
        order.setOrderStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
        paymentRepository.save(payment);
        List<OrderItem> orderItem = order.getOrderItems();
        for (OrderItem o : orderItem) {
            int q = o.getProduct().getQuantity() - o.getQuantity();
            Product pro = o.getProduct();
            pro.setQuantity(q);
            productRepository.save(pro);
        }
        Optional<Cart> cartOptional = cartRepository.findByUser(order.getUser());
            if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
          List<CartItem> cartItems = cartItemRepository.findByCart(cart);
          cartItemRepository.deleteAll(cartItems);
}

        return toPaymentResponseDto(payment);
    }
}
