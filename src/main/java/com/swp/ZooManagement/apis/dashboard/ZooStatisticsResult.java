package com.swp.ZooManagement.apis.dashboard;

import com.swp.ZooManagement.apis.animalspecies.GetAnimalSpeciesStatisticsResult;
import lombok.Data;

import java.util.List;

@Data
public class ZooStatisticsResult {
    private long totalArea;
    private long totalCage;
    private long totalNewsHidden;
    private long totalNewsPublished;
    private long totalStaff;
    private long totalTrainer;
    private long totalAnimal;
    private long totalAnimalSick;
    private long totalAnimalInDanger;
    private long totalAnimalSpecies;
    private List<GetAnimalSpeciesStatisticsResult> animalSpeciesStatistics;
}
