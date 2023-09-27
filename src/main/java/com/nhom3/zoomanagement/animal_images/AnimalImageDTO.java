package com.nhom3.zoomanagement.animal_images;

import com.nhom3.zoomanagement.animals.AnimalDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalImageDTO {
    public static AnimalImageDTO fromAnimalImage(AnimalImage animalImage, boolean hasAnimal) {
        AnimalImageDTO animalImageDTO = new AnimalImageDTO();
        animalImageDTO.setId(animalImage.getId());
        animalImageDTO.setUrl(animalImageDTO.getUrl());
        if (hasAnimal) {
            animalImageDTO.setAnimal(AnimalDTO.fromAnimal(animalImage.getAnimal(), false, false));
        }
        return animalImageDTO;
    }

    public static List<AnimalImageDTO> fromAnimalImageList(List<AnimalImage> animalImageList, boolean hasAnimal) {
        List<AnimalImageDTO> animalImageDTOList = new ArrayList<>();
        for (AnimalImage animalImage : animalImageList) {
            AnimalImageDTO animalImageDTO = fromAnimalImage(animalImage, hasAnimal);
            animalImageDTOList.add(animalImageDTO);
        }
        return animalImageDTOList;
    }
    private Integer id;
    private String url;
    private AnimalDTO animal;
}
