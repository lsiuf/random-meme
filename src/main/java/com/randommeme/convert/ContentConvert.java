package com.randommeme.convert;

import com.randommeme.dto.ContentDto;
import com.randommeme.dto.ContentOutDto;
import com.randommeme.entity.ContentPo;
import org.mapstruct.Mapper;
import org.mapstruct.Builder;

import java.util.List;

/**
 * @ClassName ContentConvert
 * @Description 实体类型转换器
 * @Author liweihua
 * @Date 2020/9/4 9:07
 * @Version 1.0
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ContentConvert {

    ContentDto poToDto(ContentPo contentPo);

    ContentOutDto poToOutDto(ContentPo contentPo);
}
