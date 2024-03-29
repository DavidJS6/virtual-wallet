package soe.mdeis.modulo5.virtualwallet.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import soe.mdeis.modulo5.virtualwallet.database.models.Transaction;
import soe.mdeis.modulo5.virtualwallet.database.models.Wallet;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>, JpaSpecificationExecutor<Transaction> {
    List<Transaction> findAllByDestinyWalletOrSourceWalletOrderByDate(Wallet source, Wallet destiny);
}
