package com.swp.ZooManagement.apis.dashboard;

import lombok.Data;

import java.util.List;

@Data
public class TrainerStatisticsResult {
    private GetTrainerOverallStatisticsResult overallStatistics;
    private List<GetTrainerSpeciesStatisticsResult> speciesStatistics;
}
