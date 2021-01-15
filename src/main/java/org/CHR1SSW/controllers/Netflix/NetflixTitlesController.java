package org.CHR1SSW.controllers.Netflix;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(value = "/netflixTitles")
@Api(tags = {"netflixTitles"})
public class NetflixTitlesController
{
    @Autowired
    NetflixTitlesService netflixTitlesService;
    private final File xmlSchemaFile = new File("src\\main\\resources\\schemas\\netflixXmlSchema.xsd");
    private final File jsonSchemaFile = new File("src\\main\\resources\\schemas\\netflixJsonSchema.json");

    protected boolean xmlValidator(NetflixTitles netflixTitle)
    {
        try
        {
            XmlMapper xmlMapper = new XmlMapper();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            String xml;
            xml = "<?xml version='1.0' encoding='utf-8'?>\n" +
                    "<NetflixTitlesAll xmlns=\"https://www.w3schools.com\" " +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                    " xsi:schemaLocation=\"https://www.w3schools.com netflixXmlSchema.xsd\">";
            xml += xmlMapper.writeValueAsString(netflixTitle);
            xml += "</NetflixTitlesAll>";

            Schema schema = factory.newSchema(xmlSchemaFile);

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8))));

            System.out.println("Validation for XML was successful.");
            return true;
        }
        catch (IOException | SAXException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private boolean jsonValidator(NetflixTitles netflixTitle)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
        try
        {
            URI uri = jsonSchemaFile.toURI();
            JsonNode jsonNode = objectMapper.valueToTree(netflixTitle);
            JsonSchema jsonSchema = jsonSchemaFactory.getJsonSchema(uri.toString());
            ProcessingReport validationResult = jsonSchema.validate(jsonNode);

            if (validationResult.isSuccess())
            {
                System.out.println("Validation for JSON was successful.");
                return true;
            }
            else
            {
                validationResult.forEach(vm -> System.out.println(vm.getMessage()));
                return false;
            }
        } catch (ProcessingException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates a new Netflix title if validation passes")
    public void createNetflixTitle(@RequestBody NetflixTitles netflixTitles)
    {
        if (jsonValidator(netflixTitles) && xmlValidator(netflixTitles))
        {
            this.netflixTitlesService.createNetflixTitle(netflixTitles);
        }
    }

    @GetMapping(value = "/showId={showId}", produces = {"application/json"})
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

    @GetMapping(value = "/title={title}&format=json", produces = {"application/json"})
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

    @GetMapping(value = "/format=json", produces = {"application/json"})
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
