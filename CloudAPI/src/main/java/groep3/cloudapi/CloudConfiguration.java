package groep3.cloudapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class CloudConfiguration extends Configuration 
{   
    @JsonProperty
    public SwaggerBundleConfiguration swagger;
}



