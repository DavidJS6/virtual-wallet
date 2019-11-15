package soe.mdeis.modulo5.virtualwallet.web.common.parsing;

public interface DtoEntityParser<TRequestDto, TResponseDto, TEntity>
        extends RequestDtoToEntityParser<TRequestDto, TEntity>, EntityToResponseDtoParser<TEntity, TResponseDto> {
}
