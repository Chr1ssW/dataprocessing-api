package org.CHR1SSW.controllers.Netflix;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.CHR1SSW.repositories.Netflix.NetflixTitlesRepository;
import org.CHR1SSW.services.Netflix.NetflixTitlesService;
import org.CHR1SSW.tables.Netflix.NetflixTitles;
import org.CHR1SSW.tables.Netflix.NetflixTitles;
import org.CHR1SSW.tables.Netflix.NetflixTitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/netflixTitles")
@Api(tags = {"netflixTitles"})
public class NetflixTitlesController
{
    @Autowired
    NetflixTitlesService netflixTitlesService;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates a new Netflix title if validation passes")
    public void createNetflixTitle(@RequestBody NetflixTitles netflixTitles)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
        try
        {
            File jsonSchemaFile = new File("src\\main\\resources\\schemas\\netflixJsonSchema.json");
            URI uri = jsonSchemaFile.toURI();

            JsonNode jsonNode = objectMapper.valueToTree(netflixTitles);
            JsonSchema jsonSchema = jsonSchemaFactory.getJsonSchema(uri.toString());
            ProcessingReport validationResult = jsonSchema.validate(jsonNode);

            if (validationResult.isSuccess())
            {
                System.out.println("Validation successful");
                this.netflixTitlesService.createNetflixTitle(netflixTitles);
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

    @GetMapping(value = "/showId={showId}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one Netflix title based on ID in JSON format.")
    public NetflixTitles getNetflixTitleJson(@ApiParam(value = "The id of the netflix title", required = true)
                                         @PathVariable("showId") int showId)
    {
        return netflixTitlesService.getNetflixTitle(showId);
    }

    @GetMapping(value = "/showId={showId}&format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one Netflix title based on ID in XML format.")
    public NetflixTitles getNetflixTitleXml(@ApiParam(value = "The id of the netflix title", required = true)
                                         @PathVariable("showId") int showId)
    {
        return netflixTitlesService.getNetflixTitle(showId);
    }

    @GetMapping(value = "/title={title}&format=json")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one Netflix show based on the title in JSON format.")
    public NetflixTitles getNetflixByTitleJson(@ApiParam(value = "The title of the netflix title", required = true)
                                         @PathVariable("title") String title)
    {
        return netflixTitlesService.getNetflixByTitle(title);
    }

    @GetMapping(value = "/title={title}&format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one Netflix show based on the title in XML format.")
    public NetflixTitles getNetflixByTitleXml(@ApiParam(value = "The title of the netflix title", required = true)
                                               @PathVariable("title") String title)
    {
        return netflixTitlesService.getNetflixByTitle(title);
    }

    @GetMapping(value = "/format=json")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns all the Netflix titles in JSON format.")
    public List<NetflixTitles> getAllNetflixTitlesJson()
    {
        return netflixTitlesService.getNetflixTitles();
    }

    @GetMapping(value = "/format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns all the Netflix titles in XML format.")
    public List<NetflixTitles> getAllNetflixTitlesXml()
    {
        return netflixTitlesService.getNetflixTitles();
    }

    @PutMapping(value = {"/showId={showId}"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Updates a Netflix title")
    public void updateNetflixTitle(@ApiParam(value = "A Netflix title", required = true)
                                      @PathVariable("showId") int id,
                                      @RequestBody NetflixTitles NetflixTitles)
    {
        if (netflixTitlesService.getNetflixTitle(id) != null)
        {
            netflixTitlesService.updateNetflixTitles(NetflixTitles);
        }
    }

    @DeleteMapping(value = {"/showId={showId}"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Deletes a Netflix title ")
    public void deleteNetflixTitle(@ApiParam(value = "A Netflix title", required = true)
                                      @PathVariable("showId") int id)
    {
        if (netflixTitlesService.getNetflixTitle(id) != null)
        {
            netflixTitlesService.deleteNetflixTitle(id);
        }
    }
}
