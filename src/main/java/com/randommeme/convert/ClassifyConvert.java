package com.randommeme.convert;

import com.randommeme.dto.ClassifyDto;
import com.randommeme.entity.ClassifyPo;
import org.mapstruct.Mapper;
import org.mapstruct.Builder;

import java.util.List;

/**
 * @ClassName ClassifyConvert
 * @Description 实体类型转换器
 * @Author liweihua
 * @Date 2020/9/4 9:07
 * @Version 1.0
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ClassifyConvert {

    ClassifyDto poToDto(ClassifyPo classifyPo);

    ClassifyPo dtoToPo(ClassifyDto classifyDto);

    List<ClassifyDto> listPoToDto(List<ClassifyPo> classifyPoList);
}
