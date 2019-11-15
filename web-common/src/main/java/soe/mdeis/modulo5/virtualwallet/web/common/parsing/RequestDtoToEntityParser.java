package soe.mdeis.modulo5.virtualwallet.web.common.parsing;

import java.util.List;

public interface RequestDtoToEntityParser<TRequestDto, TEntity> {
    TEntity parseRequestDtoToEntity(TRequestDto dto);

    List<TEntity> parseRequestDtosToEntities(List<TRequestDto> dtos);
}
