package soe.mdeis.modulo5.virtualwallet.web.common.parsing;

import java.util.LinkedList;
import java.util.List;

public abstract class EntityToResponseDtoParserImpl<TEntity, TResponseDto>
        implements EntityToResponseDtoParser<TEntity, TResponseDto> {
    public List<TResponseDto> parseEntitiesToResponseDtos(List<TEntity> entities) {
        List<TResponseDto> result = new LinkedList<>();
        for (TEntity originalElement : entities) {
            result.add(parseEntityToResponseDto(originalElement));
        }
        return result;
    }
}
