package org.cloudfoundry.summit.containersecurityprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
final class CredHubCaller {

    private final String endpoint;

    CredHubCaller(@Value("${credhub.endpoint}") String endpoint) {
        this.endpoint = endpoint;
    }

    @Scheduled(fixedDelay = 300_000)
    public void scheduled() {
        String s = new RestTemplate()
            .getForObject(String.format("%s/api/v1/data?paths=true", this.endpoint), String.class);

        System.out.println(s);
    }

}
