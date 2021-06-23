package com.randommeme.convert;

import com.randommeme.dto.UserDto;
import com.randommeme.entity.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.Builder;

import java.util.List;

/**
 * @ClassName UserConvert
 * @Description 实体类型转换器
 * @Author liweihua
 * @Date 2020/9/4 9:07
 * @Version 1.0
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserConvert {

    UserDto poToDto(UserPo userPo);

    UserPo dtoToPo(UserDto userDto);

    List<UserDto> listPoToDto(List<UserPo> userPoList);
}
