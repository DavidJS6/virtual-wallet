package soe.mdeis.modulo5.virtualwallet.web;

import java.util.List;

public interface EntityToResponseDtoParser<TEntity, TResponseDto> {
    TResponseDto parseEntityToResponseDto(TEntity entity);

    List<TResponseDto> parseEntitiesToResponseDtos(List<TEntity> entities);
}
