package soe.mdeis.modulo5.virtualwallet.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>, JpaSpecificationExecutor<Wallet> {
}
