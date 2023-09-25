package com.nhom3.zoomanagement.animal_images;

import com.nhom3.zoomanagement.animals.AnimalsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalImagesDTO {
    public static AnimalImagesDTO fromAnimalImage(AnimalImage animalImage, boolean hasAnimal) {
        AnimalImagesDTO animalImageDTO = new AnimalImagesDTO();
        animalImageDTO.setId(animalImage.getId());
        animalImageDTO.setUrl(animalImageDTO.getUrl());
        if (hasAnimal) {
            animalImageDTO.setAnimal(AnimalsDTO.fromAnimal(animalImage.getAnimal(), false, false));
        }
        return animalImageDTO;
    }

    public static List<AnimalImagesDTO> fromAnimalImageList(List<AnimalImage> animalImageList, boolean hasAnimal) {
        List<AnimalImagesDTO> animalImageDTOList = new ArrayList<>();
        for (AnimalImage animalImage : animalImageList) {
            AnimalImagesDTO animalImageDTO = fromAnimalImage(animalImage, hasAnimal);
            animalImageDTOList.add(animalImageDTO);
        }
        return animalImageDTOList;
    }
    private Integer id;
    private String url;
    private AnimalsDTO animal;
}
