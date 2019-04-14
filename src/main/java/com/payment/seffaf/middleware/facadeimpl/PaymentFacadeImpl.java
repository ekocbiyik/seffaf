package com.payment.seffaf.middleware.facadeimpl;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionCode;
import com.payment.seffaf.middleware.facade.IPaymentFacade;
import com.payment.seffaf.model.OrderDetail;
import com.payment.seffaf.model.OrderStatus;
import com.payment.seffaf.model.Payment;
import com.payment.seffaf.repositories.service.IOrderDetailService;
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
    private IOrderDetailService orderDetailService;

    @Override
    public Payment createPayment(Payment payment) throws SeffafException {

        paymentService.save(payment);
        logger.info("payment created: {}", payment.getPaymentId());

        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(payment.getOrderId());
        if (orderDetails.isEmpty()) {
            throw new SeffafException(SeffafExceptionCode.ORDER_NOT_FOUND, String.format("ORDER_NOT_FOUND: %s", payment.getOrderId().toString()));
        }

        for (OrderDetail detail : orderDetails) {
            detail.setOrderStatus(OrderStatus.IN_QUEUE);
            orderDetailService.save(detail);
            logger.info("orderDetail: {} status changed as {}", detail.getOrderDetailId(), detail.getOrderStatus());
        }
        return payment;
    }
}
