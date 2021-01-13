package org.CHR1SSW.controllers.DisneyPlus;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.CHR1SSW.services.DisneyPlus.DisneyPlusTitlesService;
import org.CHR1SSW.tables.DisneyPlus.DisneyPlusTitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/disneyPlusTitles")
@Api(tags = {"disneyPlusTitles"})
public class DisneyPlusTitlesController
{
    @Autowired
    DisneyPlusTitlesService disneyPlusTitlesService;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates a Disney+ title")
    public void createDisneyPlusTitle(@RequestBody DisneyPlusTitles disneyPlusTitles)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
        try
        {
            File jsonSchemaFile = new File("src\\main\\resources\\schemas\\disneyPlusJsonSchema.json");
            URI uri = jsonSchemaFile.toURI();

            JsonNode jsonNode = objectMapper.valueToTree(disneyPlusTitles);
            JsonSchema jsonSchema = jsonSchemaFactory.getJsonSchema(uri.toString());
            ProcessingReport validationResult = jsonSchema.validate(jsonNode);

            if (validationResult.isSuccess())
            {
                System.out.println("Validation successful");
                this.disneyPlusTitlesService.createDisneyPlusTitle(disneyPlusTitles);
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

    @GetMapping(value = "/id={id}&format=json")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one Disney+ title")
    public DisneyPlusTitles getDisneyPlusTitleJson(@ApiParam(value = "The id of the Disney+ title", required = true)
                                               @PathVariable("id") String id)
    {
        return disneyPlusTitlesService.getDisneyPlusTitle(id);
    }

    @GetMapping(value = "/id={id}&format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one Disney+ title")
    public DisneyPlusTitles getDisneyPlusTitleXml(@ApiParam(value = "The id of the Disney+ title", required = true)
                                               @PathVariable("id") String id)
    {
        return disneyPlusTitlesService.getDisneyPlusTitle(id);
    }

    @GetMapping(value = "/format=json")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns all the Disney+ titles")
    public List<DisneyPlusTitles> getAllDisneyPlusTitlesJson()
    {
        return disneyPlusTitlesService.getDisneyPlusTitles();
    }

    @GetMapping(value = "/format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns all the Disney+ titles")
    public List<DisneyPlusTitles> getAllDisneyPlusTitles()
    {
        return disneyPlusTitlesService.getDisneyPlusTitles();
    }

    @PutMapping(value = {"/id={id}"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Updates a Disney+ title")
    public void updateDisneyPlusTitle(@ApiParam(value = "A Disney+ title", required = true)
                                      @PathVariable("id") String id,
                                      @RequestBody DisneyPlusTitles disneyPlusTitles)
    {
        if (disneyPlusTitlesService.getDisneyPlusTitle(id) != null)
        {
            disneyPlusTitlesService.updateDisneyPlusTitles(disneyPlusTitles);
        }
    }

    @DeleteMapping(value = {"/id={id}"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Deletes a Disney+ title")
    public void deleteDisneyPlusTitle(@ApiParam(value = "A Disney+ title", required = true)
                                  @PathVariable("id") String id)
    {
        if (disneyPlusTitlesService.getDisneyPlusTitle(id) != null)
        {
            this.disneyPlusTitlesService.deleteDisneyPlusTitle(id);
        }
    }

    @GetMapping(value = "/title={title}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns Disney+ movies based on title")
    public List<DisneyPlusTitles> getDisneyPlusByTitle(@ApiParam(value = "The title of the Disney+ movie", required = true)
                                               @PathVariable("title") String title)
    {
        return disneyPlusTitlesService.findAllByTitle(title);
    }
}