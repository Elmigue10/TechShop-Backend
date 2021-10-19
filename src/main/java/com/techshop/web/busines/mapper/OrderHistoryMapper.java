package com.techshop.web.busines.mapper;

import com.techshop.web.model.dto.OrderHistoryDto;
import com.techshop.web.model.entity.OrderHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface OrderHistoryMapper extends GenericMapper<OrderHistory, OrderHistoryDto>{
    OrderHistoryMapper ORDER_HISTORY_MAPPER= Mappers.getMapper(OrderHistoryMapper.class);
}
