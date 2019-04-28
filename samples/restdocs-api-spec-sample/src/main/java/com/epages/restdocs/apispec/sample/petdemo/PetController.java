package com.epages.restdocs.apispec.sample.petdemo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.epages.restdocs.apispec.sample.petdemo.CatRequest.CAT_TYPE;
import static com.epages.restdocs.apispec.sample.petdemo.DogRequest.DOG_TYPE;
import static com.epages.restdocs.apispec.sample.petdemo.PetRequest.TYPE_PROPERTY;

/**
 * @author Liang Zhou
 */
@RestController
public class PetController
{
    @PostMapping("/pet-demo")
    public void createPet(@RequestBody Pet pet)
    {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = TYPE_PROPERTY)
    @JsonSubTypes({
        @JsonSubTypes.Type(value = Cat.class, name = CAT_TYPE),
        @JsonSubTypes.Type(value = Dog.class, name = DOG_TYPE)
    })
    public abstract static class Pet
    {
        public static final String TYPE_PROPERTY = "petType";

        private String name;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

    }

    public static class Cat extends Pet
    {
        public static final String CAT_TYPE = "cat";
        private String catProp;

        public String getCatProp()
        {
            return catProp;
        }

        public void setCatProp(String catProp)
        {
            this.catProp = catProp;
        }
    }

    public static class Dog extends Pet
    {
        public static final String DOG_TYPE = "dog";
        private String dogProp;

        public String getDogProp()
        {
            return dogProp;
        }

        public void setDogProp(String dogProp)
        {
            this.dogProp = dogProp;
        }
    }
}
