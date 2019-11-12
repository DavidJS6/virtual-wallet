package soe.mdeis.modulo5.virtualwallet.web.wallets.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.modulo5.virtualwallet.database.models.User;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;
import soe.mdeis.modulo5.virtualwallet.database.repositories.WalletRepository;
import soe.mdeis.modulo5.virtualwallet.web.parsing.DtoEntityParser;
import soe.mdeis.modulo5.virtualwallet.web.parsing.DtoEntityParserAbstractImpl;
import soe.mdeis.modulo5.virtualwallet.web.users.service.UserService;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletException;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletRequestDto;
import soe.mdeis.modulo5.virtualwallet.web.wallets.WalletResponseDto;

import java.util.List;

@Service("WalletService")
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserService userService;

    private DtoEntityParser<WalletRequestDto, WalletResponseDto, Wallet> parser = new WalletParser();

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
    }

    @Override
    public List<WalletResponseDto> findAll() {
        List<Wallet> wallets = walletRepository.findAll();
        return parser.parseEntitiesToResponseDtos(wallets);
    }

    @Override
    public WalletResponseDto findById(int id) throws WalletException {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new WalletException());
        return parser.parseEntityToResponseDto(wallet);
    }

    @Override
    public WalletResponseDto update(int id, WalletRequestDto requestDto) throws WalletException {
        Wallet walletToUpdate = walletRepository.findById(id).orElseThrow(() -> new WalletException());
        walletToUpdate.setWalletNumber(requestDto.getWalletNumber());
        User user = new User();
        user.setId(requestDto.getUserId());
        walletToUpdate.setUser(user);
        Wallet updatedWallet = walletRepository.save(walletToUpdate);
        return parser.parseEntityToResponseDto(updatedWallet);
    }

    @Override
    public WalletResponseDto store(WalletRequestDto requestDto) {
        //Wallet walletToSave = parser.parseRequestDtoToEntity(requestDto);
        Wallet walletToSave = new Wallet();
        walletToSave.setWalletNumber(requestDto.getWalletNumber());
        User user = new User();
        user.setId(requestDto.getUserId());
        walletToSave.setUser(user);
        walletToSave.setBalance(0.0);
        return parser.parseEntityToResponseDto(walletRepository.save(walletToSave));
    }

    @Override
    public void delete(int id) {
        Wallet toDelete = new Wallet();
        toDelete.setId(id);
        walletRepository.delete(toDelete);
    }

    private static class WalletParser extends DtoEntityParserAbstractImpl<WalletRequestDto, WalletResponseDto, Wallet> {

        @Override
        public WalletResponseDto parseEntityToResponseDto(Wallet wallet) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(wallet, WalletResponseDto.class);
        }

        @Override
        public Wallet parseRequestDtoToEntity(WalletRequestDto requestDto) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(requestDto, Wallet.class);
        }

    }

}
