package org.CHR1SSW.controllers.Amazon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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

@RestController
@RequestMapping(value = "/amazonTitles")
@Api(tags = {"amazonTitles"})
public class AmazonTitlesController
{
    @Autowired
    AmazonTitlesService amazonTitlesService;
    private final File xmlSchemaFile = new File("src\\main\\resources\\schemas\\amazonXmlSchema.xsd");
    private final File jsonSchemaFile = new File("src\\main\\resources\\schemas\\amazonJsonSchema.json");

    protected boolean xmlValidator(AmazonTitles amazonTitle)
    {
        try
        {
            XmlMapper xmlMapper = new XmlMapper();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            String xml;
            xml = "<?xml version='1.0' encoding='utf-8'?>\n" +
                    "<AmazonTitlesAll xmlns=\"https://www.w3schools.com\" " +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                    " xsi:schemaLocation=\"https://www.w3schools.com netflixXmlSchema.xsd\">";
            xml += xmlMapper.writeValueAsString(amazonTitle);
            xml += "</AmazonTitlesAll>";

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

    private boolean jsonValidator(AmazonTitles amazonTitle)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
        try
        {
            URI uri = jsonSchemaFile.toURI();
            JsonNode jsonNode = objectMapper.valueToTree(amazonTitle);
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
    @ApiOperation(value = "Creates an amazon title if validation passes.")
    public void createAmazonTitle(@RequestBody AmazonTitles amazonTitle)
    {
        if (jsonValidator(amazonTitle) && xmlValidator(amazonTitle))
        {
            amazonTitlesService.createAmazonTitle(amazonTitle);
        }
    }

    @GetMapping(value = "/id={id}&format=json", produces = {"application/json"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one amazon title based on ID in JSON format.")
    public AmazonTitles getAmazonTitleJson(@ApiParam(value = "The id of the amazon title", required = true)
                                       @PathVariable("id") int id)
    {
        return this.amazonTitlesService.getAmazonTitle(id);
    }

    @GetMapping(value = "/id={id}&format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one amazon title based on ID in XML format.")
    public AmazonTitles getAmazonTitleXml(@ApiParam(value = "The id of the amazon title", required = true)
                                       @PathVariable("id") int id)
    {
        return this.amazonTitlesService.getAmazonTitle(id);
    }

    @GetMapping(value = "/title={title}&format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one amazon movie based on title in XML format.")
    public AmazonTitles getAmazonTitleByTitleXml(@ApiParam(value = "The title of the Amazon movie", required = true)
                                          @PathVariable("title") String title)
    {
        return this.amazonTitlesService.getAmazonTitleByNameOfTheShow(title);
    }

    @GetMapping(value = "/title={title}&format=json", produces = {"application/json"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one amazon movie based on title in JSON format.")
    public AmazonTitles getAmazonTitleByTitleJson(@ApiParam(value = "The title of the Amazon movie", required = true)
                                                 @PathVariable("title") String title)
    {
        return this.amazonTitlesService.getAmazonTitleByNameOfTheShow(title);
    }

    @GetMapping(value = "/format=json", produces = {"application/json"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns all amazon titles in JSON format.")
    public Iterable<AmazonTitles> getAmazonTitleJson()
    {
        return this.amazonTitlesService.getAmazonTitles();
    }

    @GetMapping(value = "/format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns all amazon titles in XML format")
    public Iterable<AmazonTitles> getAmazonTitleXml()
    {
        return this.amazonTitlesService.getAmazonTitles();
    }

    @PutMapping(value = "/id={id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Updates an amazon title.")
    public void updateAmazonTitle(@ApiParam(value = "An amazon title", required = true)
                                      @PathVariable("id") int id,
                                  @RequestBody AmazonTitles amazonTitle)
    {
        if (amazonTitlesService.getAmazonTitle(id) != null)
        {
            this.amazonTitlesService.updateAmazonTitle(amazonTitle);
        }
    }

    @DeleteMapping(value = {"/id={id}"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Deletes an amazon title.")
    public void deleteAmazonTitle(@ApiParam(value = "An amazon title", required = true)
                                  @PathVariable("id") int id)
    {
        if (amazonTitlesService.getAmazonTitle(id) != null)
        {
            this.amazonTitlesService.deleteAmazonTitle(id);
        }
    }
}
