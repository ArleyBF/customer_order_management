package com.BF.customer_order_management.services.mappers;
import com.BF.customer_order_management.models.Customer;
import com.BF.customer_order_management.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EntityMapper {

    void updateCustomerRegister(@MappingTarget Customer actualCustomer,Customer newCustomer);

    void updateOrderRegister(@MappingTarget Order actualOrder, Order newOrder);
}
