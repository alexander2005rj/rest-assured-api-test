package config;

public interface DogApiEndpoints {

    String GET_BREEDS_LIST_ALL = "/breeds/list/all";
    String GET_SPECIFIC_IMAGES_BY_BREED = "/breed/{breedName}/images/";
    String GET_RANDOM_IMAGE_BREED = "/breeds/image/random";

}
