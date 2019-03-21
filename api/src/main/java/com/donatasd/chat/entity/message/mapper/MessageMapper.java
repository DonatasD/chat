package com.donatasd.chat.entity.message.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.donatasd.chat.entity.message.dto.MessageCreateDTO;
import com.donatasd.chat.entity.message.dto.MessageDTO;
import com.donatasd.chat.entity.message.model.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Message toModel(MessageCreateDTO dto);

    MessageDTO toDto(Message model);
}
