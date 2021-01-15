package org.CHR1SSW.controllers.DisneyPlus;


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
import org.CHR1SSW.services.DisneyPlus.DisneyPlusTitlesService;
import org.CHR1SSW.tables.DisneyPlus.DisneyPlusTitles;
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
@RequestMapping(value = "/disneyPlusTitles")
@Api(tags = {"disneyPlusTitles"})
public class DisneyPlusTitlesController {
    @Autowired
    DisneyPlusTitlesService disneyPlusTitlesService;
    private final File xmlSchemaFile = new File("src\\main\\resources\\schemas\\disneyPlusXmlSchema.xsd");
    private final File jsonSchemaFile = new File("src\\main\\resources\\schemas\\disneyPlusJsonSchema.json");

    protected boolean xmlValidator(DisneyPlusTitles disneyPlusTitles) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            String xml;
            xml = "<?xml version='1.0' encoding='utf-8'?>\n" +
                    "<DisneyPlusTitlesAll xmlns=\"https://www.w3schools.com\" " +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                    " xsi:schemaLocation=\"https://www.w3schools.com netflixXmlSchema.xsd\">";
            xml += xmlMapper.writeValueAsString(disneyPlusTitles);
            xml += "</DisneyPlusTitlesAll>";

            Schema schema = factory.newSchema(xmlSchemaFile);

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8))));

            System.out.println("Validation for XML was successful.");
            return true;
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean jsonValidator(DisneyPlusTitles disneyPlusTitles) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
        try {
            URI uri = jsonSchemaFile.toURI();
            JsonNode jsonNode = objectMapper.valueToTree(disneyPlusTitles);
            JsonSchema jsonSchema = jsonSchemaFactory.getJsonSchema(uri.toString());
            ProcessingReport validationResult = jsonSchema.validate(jsonNode);

            if (validationResult.isSuccess()) {
                System.out.println("Validation for JSON was successful.");
                return true;
            } else {
                validationResult.forEach(vm -> System.out.println(vm.getMessage()));
                return false;
            }
        } catch (ProcessingException e) {
            e.printStackTrace();
        }

        return false;
    }


    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates a Disney+ title")
    public void createDisneyPlusTitle(@RequestBody DisneyPlusTitles disneyPlusTitles) {
        if (jsonValidator(disneyPlusTitles) && xmlValidator(disneyPlusTitles)) {
            disneyPlusTitlesService.createDisneyPlusTitle(disneyPlusTitles);
        }
    }

    @GetMapping(value = "/id={id}&format=json", produces = {"application/json"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one Disney+ title based on ID in JSON format.")
    public DisneyPlusTitles getDisneyPlusTitleJson(@ApiParam(value = "The id of the Disney+ title", required = true)
                                                   @PathVariable("id") String id) {
        return disneyPlusTitlesService.getDisneyPlusTitle(id);
    }

    @GetMapping(value = "/id={id}&format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one Disney+ title based on ID in XML format.")
    public DisneyPlusTitles getDisneyPlusTitleXml(@ApiParam(value = "The id of the Disney+ title", required = true)
                                                  @PathVariable("id") String id) {
        return disneyPlusTitlesService.getDisneyPlusTitle(id);
    }

    @GetMapping(value = "/format=json", produces = {"application/json"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns all the Disney+ titles in JSON format.")
    public List<DisneyPlusTitles> getAllDisneyPlusTitlesJson() {
        return disneyPlusTitlesService.getDisneyPlusTitles();
    }

    @GetMapping(value = "/format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns all the Disney+ titles in XML format.")
    public List<DisneyPlusTitles> getAllDisneyPlusTitlesXml() {
        return disneyPlusTitlesService.getDisneyPlusTitles();
    }


    @GetMapping(value = "/title={title}&format=json", produces = {"application/json"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns Disney+ movies based on title in JSON format.")
    public List<DisneyPlusTitles> getDisneyPlusByTitleJson(@ApiParam(value = "The title of the Disney+ movie", required = true)
                                                           @PathVariable("title") String title) {
        return disneyPlusTitlesService.findAllByTitle(title);
    }

    @GetMapping(value = "/title={title}&format=xml", produces = {"application/xml"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns Disney+ movies based on title in XML format.")
    public List<DisneyPlusTitles> getDisneyPlusByTitleXml(@ApiParam(value = "The title of the Disney+ movie", required = true)
                                                          @PathVariable("title") String title) {
        return disneyPlusTitlesService.findAllByTitle(title);
    }

    @PutMapping(value = {"/id={id}"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Updates a Disney+ title based on ID.")
    public void updateDisneyPlusTitle(@ApiParam(value = "A Disney+ title", required = true)
                                      @PathVariable("id") String id,
                                      @RequestBody DisneyPlusTitles disneyPlusTitles) {
        if (disneyPlusTitlesService.getDisneyPlusTitle(id) != null) {
            disneyPlusTitlesService.updateDisneyPlusTitles(disneyPlusTitles);
        }
    }

    @DeleteMapping(value = {"/id={id}"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Deletes a Disney+ title based on ID.")
    public void deleteDisneyPlusTitle(@ApiParam(value = "A Disney+ title", required = true)
                                      @PathVariable("id") String id) {
        if (disneyPlusTitlesService.getDisneyPlusTitle(id) != null) {
            this.disneyPlusTitlesService.deleteDisneyPlusTitle(id);
        }
    }


}