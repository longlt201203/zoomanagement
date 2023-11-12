package com.swp.ZooManagement.apis.dashboard;

public interface GetTrainerOverallStatisticsResult {
    Long getTotalAnimalHealthy();
    Long getTotalAnimalSick();
    Long getTotalAnimalInDanger();
    Long getTotalAnimal();
    Long getTotalCage();
}
