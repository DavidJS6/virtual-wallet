package soe.mdeis.modulo5.virtualwallet.web;

import java.util.LinkedList;
import java.util.List;

public abstract class DtoEntityParserAbstractImpl<TRequestDto, TResponseDto, TEntity>
        implements DtoEntityParser<TRequestDto, TResponseDto, TEntity> {
    public List<TResponseDto> parseEntitiesToResponseDtos(List<TEntity> entities) {
        List<TResponseDto> result = new LinkedList<>();
        for (TEntity originalElement : entities) {
            result.add(parseEntityToResponseDto(originalElement));
        }
        return result;
    }

    public List<TEntity> parseRequestDtosToEntities(List<TRequestDto> dtos) {
        List<TEntity> result = new LinkedList<>();
        for (TRequestDto originalElement : dtos) {
            result.add(parseRequestDtoToEntity(originalElement));
        }
        return result;
    }
}
