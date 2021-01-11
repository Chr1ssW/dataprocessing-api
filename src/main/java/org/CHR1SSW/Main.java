package org.CHR1SSW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Main extends SpringBootServletInitializer
{
    private static final Class<Main> main = Main.class;
    public static void main(String[] args)
    {
        SpringApplication.run(main, args);
    }
}
