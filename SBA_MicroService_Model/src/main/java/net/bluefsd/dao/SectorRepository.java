package net.bluefsd.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import net.bluefsd.entity.Sector;
import net.bluefsd.entity.StockPrice;

@Repository(value = "sbaSectorService")
public interface SectorRepository extends JpaRepository<StockPrice, Long> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM Sector s")
	public List<Sector> listSectorNames();

}
