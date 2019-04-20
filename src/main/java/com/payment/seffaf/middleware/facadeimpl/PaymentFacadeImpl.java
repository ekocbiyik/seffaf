package com.payment.seffaf.middleware.facadeimpl;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionCode;
import com.payment.seffaf.middleware.facade.IPaymentFacade;
import com.payment.seffaf.model.*;
import com.payment.seffaf.repositories.service.IOrderDetailService;
import com.payment.seffaf.repositories.service.IOrderService;
import com.payment.seffaf.repositories.service.IPaymentDetailService;
import com.payment.seffaf.repositories.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * ekocbiyik on 4/14/19
 */
@Controller
public class PaymentFacadeImpl implements IPaymentFacade {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IPaymentDetailService paymentDetailService;

    @Override
    public Payment createPayment(Payment payment) throws SeffafException {

        Order order = orderService.getOrderByOrderId(payment.getOrderId());
        if (order == null) {
            throw new SeffafException(SeffafExceptionCode.ORDER_NOT_FOUND, String.format("ORDER_NOT_FOUND: %s", payment.getOrderId()));
        }
        logger.info("order found: {}", order.getOrderId());

        // order'a ait totalAmount ve currency bilgileri burada set edilecek
        payment.setDeliveredCustomerId(order.getDeliveredCustomerId());
        payment.setPaymentAmount(order.getTotalAmount());
        payment.setCurrency(order.getCurrency());
        paymentService.save(payment);
        logger.info("payment created: {}", payment.getPaymentId());

        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(payment.getOrderId());
        if (orderDetails.isEmpty()) {
            throw new SeffafException(SeffafExceptionCode.ORDER_DETAILS_NOT_FOUND, String.format("ORDER_NOT_FOUND: %s", payment.getOrderId().toString()));
        }
        logger.info("order details found {}", orderDetails.size());

        for (OrderDetail oDetail : orderDetails) {
            oDetail.setOrderStatus(OrderStatus.IN_QUEUE);
            orderDetailService.save(oDetail);
            logger.info("orderDetail: {} status changed as {}", oDetail.getOrderDetailId(), oDetail.getOrderStatus());

            // herbir orderDetail için paymentDetail oluşturuyoruz
            PaymentDetail pDetail = new PaymentDetail();
            pDetail.setPaymentId(payment.getPaymentId());
            pDetail.setOrderDetailId(oDetail.getOrderDetailId());
            pDetail.setSellerCustomerId(oDetail.getSellerCustomerId());
            pDetail.setDeliveredCustomerId(oDetail.getDeliveredCustomerId());
            pDetail.setPaymentFlow(PaymentFlow.TO_SELLER); // ücret ilk olarak müşteriden satıcıya
            pDetail.setPaymentStatus(PaymentStatus.WAITING); // ücret ilk olarak seffaf hesabında

            paymentDetailService.save(pDetail);
            logger.info("paymentDetail created: {} status: {}", pDetail.getPaymentDetailId(), pDetail.getPaymentStatus());
            // TODO: 4/20/19 burada mail atılacak...
        }
        return payment;
    }
}
