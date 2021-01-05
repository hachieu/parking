package cloud.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cloud.entity.Parking;
import cloud.query.ParkingQuery;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer>{
	
	@Query(value = ParkingQuery.GETSLOT, nativeQuery = true)
	int getSlotParking(int id);
	
	@Query(value = ParkingQuery.COUNTVEHICLE, nativeQuery = true)
	int countVehicle(String status, int id);
}
