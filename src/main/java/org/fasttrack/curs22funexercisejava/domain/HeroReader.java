package org.fasttrack.curs22funexercisejava.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class HeroReader {

    private final String fileLocation;
    private final Resource file;

    public HeroReader(@Value("${heroes.location:default.txt}") String fileLocation) {
        this.fileLocation = fileLocation;
        this.file = new ClassPathResource(fileLocation);
        if (!file.exists()) {
            throw new RuntimeException("Could not find file in classpath " + fileLocation);
        }
    }

    public List<Hero> read() {
        List<Hero> result = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("[|]");
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split("[|]");
                result.add(new Hero(
                        tokens[0],
                        Integer.parseInt(tokens[1]),
                        Integer.parseInt(tokens[2])));
            }
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
        return result;
    }
}
