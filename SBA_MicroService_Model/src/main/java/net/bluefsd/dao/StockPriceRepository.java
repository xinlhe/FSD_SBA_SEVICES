package net.bluefsd.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bluefsd.entity.StockPrice;

@Repository(value = "stockPriceRepository")
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

	// -----------------------------------------------------------------------------------------------------------
	// below methods are to retrieve prices with all the time
	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFWeekDay bfw) order by s.curTime")
	public List<StockPrice> findWeekByStockCd(@Param("stockCd") String stockCd);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFMonthDay bfm )  order by s.curTime")
	public List<StockPrice> findMonthByStockCd(@Param("stockCd") String stockCd);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFQuarterDay bfm )  order by s.curTime")
	public List<StockPrice> findQuarterByStockCd(@Param("stockCd") String stockCd);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFYearDay bfm )  order by s.curTime")
	public List<StockPrice> findYearByStockCd(@Param("stockCd") String stockCd);

	// -----------------------------------------------------------------------------------------------------------
	// below methods are to retrieve prices for a company in a special period
	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFWeekDay bfw where date(lastDay) between :fromDate and :toDate) order by s.curTime")
	public List<StockPrice> findWeekByStockCd(@Param("stockCd") String stockCd, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFMonthDay bfm where date(lastDay) between :fromDate and :toDate)  order by s.curTime")
	public List<StockPrice> findMonthByStockCd(@Param("stockCd") String stockCd, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFQuarterDay bfm where date(lastDay) between :fromDate and :toDate)  order by s.curTime")
	public List<StockPrice> findQuarterByStockCd(@Param("stockCd") String stockCd,  @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Transactional(readOnly = true)
	@Query(value = "SELECT s FROM StockPrice s where stockCd=:stockCd and curTime in (select lastDay from BFYearDay bfm where date(lastDay) between :fromDate and :toDate)  order by s.curTime")
	public List<StockPrice> findYearByStockCd(@Param("stockCd") String stockCd,  @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);
	//-------------------------------------------------------------------------------
	//below methods are to retrieve data for sector
	//select avg(price),  cur_time from stock_price  where  cur_time  in (select last_day from bfmonthday) group by  cur_time;
	@Transactional(readOnly = true)
	@Query(value = "select avg(price) as price, date_format(curTime,'%Y-%m-%d') as cur_date from StockPrice sp "
			+ "where curTime  in (select lastDay from BFWeekDay) and "
			+ "stockCd in (select stockCd from Stock where sectorCd=:sectorCd) group by  curTime order by curTime")
	public List<Object[]> findWeekBySectorCd(@Param("sectorCd") String sectorCd);
	
	@Transactional(readOnly = true)
	@Query(value = "select avg(price) as price, date_format(curTime,'%Y-%m-%d') as cur_date from StockPrice sp "
			+ "where curTime  in (select lastDay from BFMonthDay) and "
			+ "stockCd in (select stockCd from Stock where sectorCd=:sectorCd) group by  curTime order by curTime")
	public List<Object[]> findMonthBySectorCd(@Param("sectorCd") String sectorCd);
	
	@Transactional(readOnly = true)
	@Query(value = "select avg(price) as price, date_format(curTime,'%Y-%m-%d') as cur_date from StockPrice sp "
			+ "where curTime  in (select lastDay from BFQuarterDay) and "
			+ "stockCd in (select stockCd from Stock where sectorCd=:sectorCd) group by  curTime order by curTime")
	public List<Object[]> findQuarterBySectorCd(@Param("sectorCd") String sectorCd);

	@Transactional(readOnly = true)
	@Query(value = "select avg(price) as price, date_format(curTime,'%Y-%m-%d') as cur_date from StockPrice sp "
			+ "where curTime  in (select lastDay from BFYearDay) and "
			+ "stockCd in (select stockCd from Stock where sectorCd=:sectorCd) group by  curTime order by curTime")
	public List<Object[]> findYearBySectorCd(@Param("sectorCd") String sectorCd);

	//--------------------------------------------------------------------------------
	//below methods are to retrieve data for sector for a special period
	
	@Transactional(readOnly = true)
	@Query(value = "select avg(price) as price, date_format(curTime,'%Y-%m-%d') as cur_date from StockPrice sp "
			+ "where curTime  in (select lastDay from BFWeekDay  bfw where date(lastDay) between :fromDate and :toDate) and "
			+ "stockCd in (select stockCd from Stock s where sectorCd=:sectorCd) group by  curTime order by curTime")
	public List<Object[]> findWeekBySectorCd(@Param("sectorCd") String sectorCd, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);
	
	@Transactional(readOnly = true)
	@Query(value = "select avg(price) as price, date_format(curTime,'%Y-%m-%d') as cur_date from StockPrice sp "
			+ "where curTime  in (select lastDay from BFMonthDay  bfw where date(lastDay) between :fromDate and :toDate) and "
			+ "stockCd in (select stockCd from Stock where sectorCd=:sectorCd) group by  curTime order by curTime")
	public List<Object[]> findMonthBySectorCd(@Param("sectorCd") String sectorCd, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);
	
	@Transactional(readOnly = true)
	@Query(value = "select avg(price) as price, date_format(curTime,'%Y-%m-%d') as cur_date from StockPrice sp "
			+ "where curTime  in (select lastDay from BFQuarterDay  bfw where date(lastDay) between :fromDate and :toDate) and "
			+ "stockCd in (select stockCd from Stock where sectorCd=:sectorCd) group by  curTime order by curTime")
	public List<Object[]> findQuarterBySectorCd(@Param("sectorCd") String sectorCd, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Transactional(readOnly = true)
	@Query(value = "select avg(price) as price, date_format(curTime,'%Y-%m-%d') as cur_date from StockPrice sp "
			+ "where curTime  in (select lastDay from BFYearDay  bfw where date(lastDay) between :fromDate and :toDate) and "
			+ "stockCd in (select stockCd from Stock where sectorCd=:sectorCd) group by  curTime order by curTime")
	public List<Object[]> findYearBySectorCd(@Param("sectorCd") String sectorCd, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

}
