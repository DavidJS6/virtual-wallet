package soe.mdeis.modulo5.virtualwallet.web;

public interface DtoEntityParser<TRequestDto, TResponseDto, TEntity>
        extends RequestDtoToEntityParser<TRequestDto, TEntity>, EntityToResponseDtoParser<TEntity, TResponseDto> {
}
