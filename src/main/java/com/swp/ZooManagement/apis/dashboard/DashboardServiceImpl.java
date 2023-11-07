package com.swp.ZooManagement.apis.dashboard;

import com.swp.ZooManagement.apis.animalspecies.AnimalSpeciesRepository;
import com.swp.ZooManagement.apis.tickets.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        result.setTicketDistribution(ticketsRepository.getTicketDistribution(params.getStartDate(), params.getEndDate()));
        switch (params.getType()) {
            case WEEK: {
                result.setOverallStatistics(dashboardRepository.getSalesReportByWeek(params.getStartDate(), params.getEndDate()));
                break;
            }
            case MONTH: {
                result.setOverallStatistics(dashboardRepository.getSalesReportByMonth(params.getYear(), params.getMonth()));
                break;
            }
            case YEAR: {
                result.setOverallStatistics(dashboardRepository.getSalesReportByYear(params.getYear()));
                break;
            }
        }
        return result;
    }
}
