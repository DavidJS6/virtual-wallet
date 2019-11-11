package soe.mdeis.modulo5.virtualwallet.web.users.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.modulo5.virtualwallet.database.models.User;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;
import soe.mdeis.modulo5.virtualwallet.database.repositories.UserRepository;
import soe.mdeis.modulo5.virtualwallet.database.repositories.WalletRepository;
import soe.mdeis.modulo5.virtualwallet.web.DtoEntityParser;
import soe.mdeis.modulo5.virtualwallet.web.DtoEntityParserAbstractImpl;
import soe.mdeis.modulo5.virtualwallet.web.EntityToResponseDtoParserImpl;
import soe.mdeis.modulo5.virtualwallet.web.users.UserException;
import soe.mdeis.modulo5.virtualwallet.web.users.UserRequestDto;
import soe.mdeis.modulo5.virtualwallet.web.users.UserResponseDto;
import soe.mdeis.modulo5.virtualwallet.web.users.WalletResponseDto;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
//    private static final DtoEntityParser<UserRequestDto, UserResponseDto, User> parser
//            = new UserParser();
    private DtoEntityParser<UserRequestDto, UserResponseDto, User> parser = new UserParser();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        return parser.parseEntitiesToResponseDtos(users);
    }

    @Override
    public UserResponseDto findById(int id) throws UserException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException());
        return parser.parseEntityToResponseDto(user);
    }

    @Override
    public UserResponseDto update(int id, UserRequestDto userRequest) {
        User userToUpdate = parser.parseRequestDtoToEntity(userRequest);
        userToUpdate.setId(id);
        User updatedUser = userRepository.save(userToUpdate);
        return parser.parseEntityToResponseDto(updatedUser);
    }

    @Override
    public UserResponseDto store(UserRequestDto userRequestDto) {
        User userToSave = parser.parseRequestDtoToEntity(userRequestDto);
        User savedUser = userRepository.save(userToSave);
        return parser.parseEntityToResponseDto(savedUser);
    }

    @Override
    public void delete(int id) {
        User toDelete = new User();
        toDelete.setId(id);
        userRepository.delete(toDelete);
    }

    @Override
    public List<WalletResponseDto> getUserWallets(int id) {
        User user = new User();
        user.setId(id);
        List<Wallet> wallets = walletRepository.findByUser(user);
        return new WalletParserResponse().parseEntitiesToResponseDtos(wallets);
    }

    private static class UserParser extends DtoEntityParserAbstractImpl<UserRequestDto, UserResponseDto, User> {
        @Override
        public UserResponseDto parseEntityToResponseDto(User user) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(user, UserResponseDto.class);
        }

        @Override
        public User parseRequestDtoToEntity(UserRequestDto userRequestDto) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(userRequestDto, User.class);
        }
    }

    private static class WalletParserResponse extends EntityToResponseDtoParserImpl<Wallet, WalletResponseDto> {

        @Override
        public WalletResponseDto parseEntityToResponseDto(Wallet wallet) {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(wallet, WalletResponseDto.class);
        }
    }
}
