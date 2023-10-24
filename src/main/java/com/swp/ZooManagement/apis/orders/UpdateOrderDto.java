package com.swp.ZooManagement.apis.orders;

import com.swp.ZooManagement.core.DtoBase;
import com.swp.ZooManagement.utils.IsEnum;
import com.swp.ZooManagement.utils.enums.OrderStatusEnum;
import lombok.Data;

@Data
public class UpdateOrderDto implements DtoBase<MyOrder> {
    @IsEnum(enumClass = OrderStatusEnum.class)
    private String status;

    @Override
    public MyOrder toEntity() {
        MyOrder order = new MyOrder();
        order.setStatus(OrderStatusEnum.valueOf(status));
        return order;
    }
}
