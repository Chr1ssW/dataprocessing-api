package org.CHR1SSW.controllers.Amazon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.CHR1SSW.services.Amazon.AmazonTitlesService;
import org.CHR1SSW.tables.Amazon.AmazonTitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URI;

@RestController
@RequestMapping(value = "/amazonTitles")
@Api(tags = {"amazonTitles"})
public class AmazonTitlesController
{
    @Autowired
    AmazonTitlesService amazonTitlesService;


    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates an amazon title")
    public void createAmazonTitle(@RequestBody AmazonTitles amazonTitle)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
        try
        {
            File jsonSchemaFile = new File("src\\main\\resources\\schemas\\amazonJsonSchema.json");
            URI uri = jsonSchemaFile.toURI();

            JsonNode jsonNode = objectMapper.valueToTree(amazonTitle);
            JsonSchema jsonSchema = jsonSchemaFactory.getJsonSchema(uri.toString());
            ProcessingReport validationResult = jsonSchema.validate(jsonNode);

            if (validationResult.isSuccess())
            {
                System.out.println("Validation successful");
                this.amazonTitlesService.createAmazonTitle(amazonTitle);
            }
            else
            {
                validationResult.forEach(vm -> System.out.println(vm.getMessage()));
            }
        }
        catch (ProcessingException e)
        {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one amazon title")
    public AmazonTitles getAmazonTitle(@ApiParam(value = "The id of the amazon title", required = true)
                                       @PathVariable("id") int id)
    {
        return this.amazonTitlesService.getAmazonTitle(id);
    }

    @GetMapping(value = "")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns all amazon titles")
    public Iterable<AmazonTitles> getAmazonTitle()
    {
        return this.amazonTitlesService.getAmazonTitles();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Updates an amazon title")
    public void updateAmazonTitle(@ApiParam(value = "An amazon title", required = true)
                                      @PathVariable("id") int id,
                                  @RequestBody AmazonTitles amazonTitle)
    {
        if (amazonTitlesService.getAmazonTitle(id) != null)
        {
            this.amazonTitlesService.updateAmazonTitle(amazonTitle);
        }
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Deletes an amazon title")
    public void deleteAmazonTitle(@ApiParam(value = "An amazon title", required = true)
                                  @PathVariable("id") int id)
    {
        if (amazonTitlesService.getAmazonTitle(id) != null)
        {
            this.amazonTitlesService.deleteAmazonTitle(id);
        }
    }
}
