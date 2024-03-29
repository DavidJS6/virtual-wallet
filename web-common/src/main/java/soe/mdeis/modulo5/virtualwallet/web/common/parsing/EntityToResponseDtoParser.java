package soe.mdeis.modulo5.virtualwallet.web.common.parsing;

import java.util.List;

public interface EntityToResponseDtoParser<TEntity, TResponseDto> {
    TResponseDto parseEntityToResponseDto(TEntity entity);

    List<TResponseDto> parseEntitiesToResponseDtos(List<TEntity> entities);
}
