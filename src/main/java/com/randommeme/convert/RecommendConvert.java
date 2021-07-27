package com.randommeme.convert;

import com.randommeme.dto.RecommendCountDto;
import com.randommeme.dto.RecommendDto;
import com.randommeme.entity.RecommendCountPo;
import com.randommeme.entity.RecommendPo;
import org.mapstruct.Mapper;
import org.mapstruct.Builder;

import java.util.List;

/**
 * @ClassName RecommendConvert
 * @Description 实体类型转换器
 * @Author liweihua
 * @Date 2020/9/4 9:07
 * @Version 1.0
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RecommendConvert {

    RecommendDto poToDto(RecommendPo recommendPo);

    RecommendPo dtoToPo(RecommendDto recommendDto);

    List<RecommendDto> listPoToDto(List<RecommendPo> recommendPoList);

    RecommendCountDto countPoToDto(RecommendCountPo po);
}
