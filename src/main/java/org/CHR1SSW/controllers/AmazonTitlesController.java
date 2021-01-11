package org.CHR1SSW.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.CHR1SSW.services.AmazonTitleService;
import org.CHR1SSW.tables.AmazonTitles;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "amazonTitles")
@Api(tags = {"amazonTitles"})
public class AmazonTitlesController
{
    AmazonTitleService amazonTitleService;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates an amazon title")
    public void createAmazonTitle(@RequestBody AmazonTitles amazonTitle)
    {
        this.amazonTitleService.createAmazonTitle(amazonTitle);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns one amazon title")
    public AmazonTitles getAmazonTitle(@ApiParam(value = "The id of the amazon title", required = true)
                                       @PathVariable("id") int id)
    {
        return this.amazonTitleService.getAmazonTitle(id);
    }

    @GetMapping(value = "")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Returns all amazon titles")
    public Iterable<AmazonTitles> getAmazonTitle()
    {
        return this.amazonTitleService.getAmazonTitles();
    }

    @PutMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Updates an amazon title")
    public void updateAmazonTitle(@ApiParam(value = "An amazon title", required = true)
                                  @RequestBody AmazonTitles amazonTitle)
    {
        this.amazonTitleService.updateAmazonTitle(amazonTitle);
    }

    @DeleteMapping(value = {"{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Deletes and amazon title")
    public void deleteAmazonTitle(@ApiParam(value = "An amazon title", required = true)
                                  @PathVariable("id") int id)
    {
        this.amazonTitleService.deleteAmazonTitle(id);
    }
}
