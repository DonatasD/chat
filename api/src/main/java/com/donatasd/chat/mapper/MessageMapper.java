package com.donatasd.chat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.donatasd.chat.dto.MessageCreateDTO;
import com.donatasd.chat.dto.MessageDTO;
import com.donatasd.chat.model.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Message toModel(MessageCreateDTO dto);

    MessageDTO toDto(Message model);
}
