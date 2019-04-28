package com.epages.restdocs.apispec.sample.petdemo;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Liang Zhou
 */
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
public class PetControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @Test
    @Tag("my-test")
    public void testCreateCat() throws Exception
    {
        mockMvc.perform(post("/pet-demo")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n"
                + "    \"petType\": \"cat\",\n"
                + "    \"name\": \"a cat\",\n"
                + "    \"catProp\": \"a cat specific prop\"\n"
                + "}"))
            .andExpect(status().isOk())
            .andDo(document("create-cat", resource(
                ResourceSnippetParameters.builder()
                    .description("Create a cat")
                    .requestFields(
                        fieldWithPath("petType").description("Type discriminator. Possible value: 'dog', 'cat'"),
                        fieldWithPath("name").description("Name of cat"),
                        fieldWithPath("catProp").description("A cat specific property")
                    )
                    .build()
            )));
    }

    @Test
    @Tag("my-test")
    public void testCreateDog() throws Exception
    {
        mockMvc.perform(post("/pet-demo")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n"
                + "    \"petType\": \"dog\",\n"
                + "    \"name\": \"a dog\",\n"
                + "    \"dogProp\": \"a dog specific prop\"\n"
                + "}"))
            .andExpect(status().isOk())
            .andDo(document("create-dog", resource(
                ResourceSnippetParameters.builder()
                    .description("Create a dog")
                    .requestFields(
                        fieldWithPath("petType").description("Type discriminator. Possible value: 'dog', 'cat'"),
                        fieldWithPath("name").description("Name of dog"),
                        fieldWithPath("dogProp").description("A dog specific property")
                    )
                    .build()
            )));
    }
}
