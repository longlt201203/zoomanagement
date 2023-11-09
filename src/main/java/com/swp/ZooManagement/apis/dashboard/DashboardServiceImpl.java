package com.swp.ZooManagement.apis.dashboard;

import com.swp.ZooManagement.apis.animalspecies.AnimalSpeciesRepository;
import com.swp.ZooManagement.apis.tickets.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private AnimalSpeciesRepository animalSpeciesRepository;

    @Autowired
    private TicketsRepository ticketsRepository;

    @Override
    public ZooStatisticsResult getZooStatistics() {
        ZooStatisticsResult result = new ZooStatisticsResult();
        GetZooStatisticsResult getZooStatisticsResult = dashboardRepository.getZooStatistics();
        result.setTotalAnimal(getZooStatisticsResult.getTotalAnimal());
        result.setTotalAnimalInDanger(getZooStatisticsResult.getTotalAnimalInDanger());
        result.setTotalAnimalSick(getZooStatisticsResult.getTotalAnimalSick());
        result.setTotalArea(getZooStatisticsResult.getTotalArea());
        result.setTotalNewsHidden(getZooStatisticsResult.getTotalNewsHidden());
        result.setTotalNewsPublished(getZooStatisticsResult.getTotalNewsPublished());
        result.setTotalStaff(getZooStatisticsResult.getTotalStaff());
        result.setTotalTrainer(getZooStatisticsResult.getTotalTrainer());
        result.setTotalCage(getZooStatisticsResult.getTotalCage());
        result.setTotalAnimalSpecies(animalSpeciesRepository.count());
        result.setAnimalSpeciesStatistics(animalSpeciesRepository.getAnimalSpeciesStatistics());
        return result;
    }

    @Override
    public SaleReportResult getSaleReport(GetSaleReportQueryParams params) {
        SaleReportResult result = new SaleReportResult();
        switch (params.getType()) {
            case DAY: {
                result.setOverallStatistics(dashboardRepository.getSalesReportByDay(params.getStartDate(), params.getEndDate()));
                break;
            }
            case MONTH: {
                LocalTime midNight = LocalTime.of(0, 0, 0);
                LocalDate startDate = LocalDate.of(params.getYear(), Month.JANUARY, 1);
                LocalDate endDate = LocalDate.of(params.getYear(), Month.DECEMBER, 31);
                params.setStartDate(LocalDateTime.of(startDate, midNight).toInstant(ZoneOffset.UTC));
                params.setEndDate(LocalDateTime.of(endDate, midNight).toInstant(ZoneOffset.UTC));
                result.setOverallStatistics(dashboardRepository.getSalesReportByMonth(params.getYear()));
                break;
            }
            case YEAR: {
                LocalTime midNight = LocalTime.of(0, 0, 0);
                LocalDate startDate = LocalDate.of(params.getStartYear(), Month.JANUARY, 1);
                LocalDate endDate = LocalDate.of(params.getEndYear(), Month.DECEMBER, 31);
                params.setStartDate(LocalDateTime.of(startDate, midNight).toInstant(ZoneOffset.UTC));
                params.setEndDate(LocalDateTime.of(endDate, midNight).toInstant(ZoneOffset.UTC));
                result.setOverallStatistics(dashboardRepository.getSalesReportByYear(params.getStartYear(), params.getEndYear()));
                break;
            }
        }
        result.setTicketDistribution(ticketsRepository.getTicketDistribution(params.getStartDate(), params.getEndDate()));
        return result;
    }
}
