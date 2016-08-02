package com.altoros;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * This component is intended to store useful application's information
 *
 * This component requires the following properties in the application's config file (application.[yml,properties] or bootstrap.[yml,properties])
 *
 * instanceId - could be ${spring.application.name}:${spring.application.instance_id:${server.port}}
 * or ${spring.application.name}:${vcap.application.instance_id} for CF
 *
 * eurekaUri -
 *
 * todo continue
 *
 * @author kmarabet
 */
@Component
public class ApplicationInfo implements EnvironmentAware {

  /*  @Autowired
    private Environment env;*/

    //todo retrieve artifactId with build version at start time
    private String artifactId;

    @Value("${server.port}")
    private int port;
    @Value("${spring.application.name}")
    private String appName;
    @Value("${instanceId}")
    String instanceID;

    @Value("${eureka.client.enabled}")
    Boolean eurekaEnabled;
    //	@Value("${eureka.client.serviceUrl.defaultZone}")
    @Value("${eurekaUri}")
    String eurekaUrl;

    @Value("${spring.cloud.consul.enabled}")
    Boolean consulEnabled;
    @Value("${consulUri}")
    String consulUrl;

    private String cloudAppName;
    private String cloudAppUris;
    private String activeProfiles;

    @Override
    public void setEnvironment(Environment env) {

        //eurekaEnabled = Boolean.valueOf(env.getProperty("eureka.client.enabled"));

        this.cloudAppName = env.getProperty("vcap.application.application_name");
        this.cloudAppUris = env.getProperty("vcap.application.application_uris");
        this.activeProfiles = Arrays.toString(env.getActiveProfiles());
        //this.defaultProfiles = environment.getProperty("spring.profiles.default");

        //THE System.err is used to highlight the output in to the console
        System.err.println("**************************************************************************");
        System.err.println("*** ArtifactId: " + artifactId);
        System.err.println("*** spring.application.name: " + appName);
        System.err.println("*** server.port: " + port);
        if (this.isCloudProfileSet()) {
            System.err.println("*** vcap.application.application_name: " + cloudAppName);
            System.err.println("*** vcap.application.application_uris: " + cloudAppUris);
        }
        System.err.println("*** spring.profiles.active: " + activeProfiles);
//		System.err.println("*** spring.profiles.default: " + defaultProfiles);
        System.err.println("**************************************************************************");
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public int getPort() {
        return port;
    }

    public String getAppName() {
        return appName;
    }

    public String getCloudAppName() {
        return cloudAppName;
    }

    public String getCloudAppUris() {
        return cloudAppUris;
    }

    public String getActiveProfiles() {
        return activeProfiles;
    }

    public boolean isCloudProfileSet() {
        return this.getActiveProfiles() != null && this.getActiveProfiles().contains("cloud");
    }

    public String getInstanceID() {
        return instanceID;
    }

    public Boolean isEurekaEnabled() {
        return eurekaEnabled;
    }

    public String getEurekaUrl() {
        return eurekaUrl;
    }

    public Boolean isConsulEnabled() {
        return consulEnabled;
    }

    public String getConsulUrl() {
        return consulUrl;
    }

    public String getMessage() {
        StringBuilder message = new StringBuilder();

        message.append(artifactId+" with spring.application.name: "+ this.getAppName()+", running on ");

        if (this.isCloudProfileSet()){
            message.append("the cloud under instanceId: "+this.getInstanceID());
        } else{
            message.append("port: "+this.getPort());
        }

        return message.toString();
    }

}