package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class AirportYAMLConfig {
    private AirportContext context;
    private static AirportYAMLConfig instance;


    private AirportYAMLConfig(AirportContext context){this.context=context;}

    public static AirportYAMLConfig loadConfig() {
        if(instance==null){

            var mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();
            try {
                InputStream in = AirportYAMLConfig.class.getResourceAsStream("/application.yml");
                var config = mapper.readValue(in, AirportContext.class);
                instance = new AirportYAMLConfig(config);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return instance;
    }
    public AirportContext getContext() {
        return context;
    }
}
