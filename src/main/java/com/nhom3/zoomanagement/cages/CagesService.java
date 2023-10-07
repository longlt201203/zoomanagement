package com.nhom3.zoomanagement.cages;

import com.nhom3.zoomanagement.animal_species.AnimalSpecies;
import com.nhom3.zoomanagement.animal_species.AnimalSpeciesRepository;
import com.nhom3.zoomanagement.areas.Area;
import com.nhom3.zoomanagement.areas.AreaDTO;
import com.nhom3.zoomanagement.areas.AreasRepository;
import com.nhom3.zoomanagement.areas.AreasService;
import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CagesService implements ICagesService{
    @Autowired
    CagesRepository cagesRepository;
    @Autowired
    AreasRepository areasRepository;
    @Autowired
    AnimalSpeciesRepository animalSpeciesRepository;

    @Override
    public List<CageDTO> get() {
        List<Cage> cageList = cagesRepository.findAll();
        return  CageDTO.fromCageList(cageList, true, true, false, false);
    }

    @Override
    public CageDTO get(Integer id) throws AppServiceException {
        Cage cage = cagesRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Cage not found")));
        return  CageDTO.fromCage(cage, true, true, false, false);
    }

    @Override
    public CageDTO create(CreateCageDTO dto) throws AppServiceException {
        if(cagesRepository.existsByCode(dto.getCode())){
            throw new AppServiceException(new ErrorReport("Cage code already existed"));
        }
        Cage cage = new Cage();
        Area area = areasRepository.findById(dto.getAreaId()).orElseThrow(() -> new RuntimeException("Area not found"));
        AnimalSpecies animalSpecies = animalSpeciesRepository.findById(dto.getAnimalSpeciesId()).orElseThrow(() -> new RuntimeException("Animal Species not found"));
        cage.setCode(dto.getCode());
        cage.setDescription(dto.getDescription());
        cage.setArea(area);
        cage.setAnimalSpecies(animalSpecies);
        cage = cagesRepository.save(cage);
        return CageDTO.fromCage(cage, true, true, false, false);
    }

    @Override
    public CageDTO update(Integer id, UpdateCageDTO dto) throws AppServiceException {
        Cage cage = cagesRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Cage not found")));
        if(cagesRepository.existsByCode(dto.getCode()) && !dto.getCode().matches(cage.getCode())){
            throw new AppServiceException(new ErrorReport("Cage code already existed"));
        }
        Area area = areasRepository.findById(dto.getAreaId()).orElseThrow(() -> new AppServiceException(new ErrorReport("Area not found")));
        AnimalSpecies animalSpecies = animalSpeciesRepository.findById(dto.getAnimalSpeciesId()).orElseThrow(() -> new AppServiceException(new ErrorReport("Animal Species not found")));
        cage.setCode(dto.getCode());
        cage.setDescription(dto.getDescription());
        cage.setArea(area);
        cage.setAnimalSpecies(animalSpecies);
        cage = cagesRepository.save(cage);
        return CageDTO.fromCage(cage, true, true, false, false);
    }

    @Override
    public CageDTO delete(Integer id) {
        return null;
    }
}
