package com.randommeme.convert;

import com.randommeme.dto.CommentDto;
import com.randommeme.entity.CommentPo;
import org.mapstruct.Mapper;
import org.mapstruct.Builder;

import java.util.List;

/**
 * @ClassName CommentConvert
 * @Description 实体类型转换器
 * @Author liweihua
 * @Date 2020/9/4 9:07
 * @Version 1.0
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CommentConvert {

    CommentDto poToDto(CommentPo commentPo);

    CommentPo dtoToPo(CommentDto commentDto);

    List<CommentDto> listPoToDto(List<CommentPo> commentPoList);
}
