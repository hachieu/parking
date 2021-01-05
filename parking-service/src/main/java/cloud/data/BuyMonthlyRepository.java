package cloud.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cloud.entity.BuyMonthly;
import cloud.query.BuyMonthlyQuery;

@Repository
public interface BuyMonthlyRepository extends JpaRepository<BuyMonthly, String>{
	@Query(value = BuyMonthlyQuery.CHECKBUY, nativeQuery=true)
	Optional<BuyMonthly> checkBuy(String licence, String month, String year);
	
	@Query(value = BuyMonthlyQuery.GETSTAMP, nativeQuery=true)
	Optional<BuyMonthly> getStamp(String licence, String month, String year);
}
