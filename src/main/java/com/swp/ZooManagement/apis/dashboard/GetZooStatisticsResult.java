package com.swp.ZooManagement.apis.dashboard;

public interface GetZooStatisticsResult {
    long getTotalArea();
    long getTotalCage();
    long getTotalNewsHidden();
    long getTotalNewsPublished();
    long getTotalStaff();
    long getTotalTrainer();
    long getTotalAnimal();
    long getTotalAnimalSick();
    long getTotalAnimalInDanger();
}
