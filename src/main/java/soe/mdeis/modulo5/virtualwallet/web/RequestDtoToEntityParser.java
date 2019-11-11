package soe.mdeis.modulo5.virtualwallet.web;

import java.util.List;

public interface RequestDtoToEntityParser<TRequestDto, TEntity> {
    TEntity parseRequestDtoToEntity(TRequestDto dto);

    List<TEntity> parseRequestDtosToEntities(List<TRequestDto> dtos);
}
