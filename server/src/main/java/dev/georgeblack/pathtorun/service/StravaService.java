package dev.georgeblack.pathtorun.service;

import dev.georgeblack.pathtorun.model.Region;
import dev.georgeblack.pathtorun.model.strava.StravaSegment;
import dev.georgeblack.pathtorun.model.strava.StravaSegments;
import dev.georgeblack.pathtorun.model.strava.StravaTokenRefreshRequest;
import dev.georgeblack.pathtorun.model.strava.StravaTokenRefreshResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;

@Repository
public class StravaService {
    Logger logger = LoggerFactory.getLogger(StravaService.class);

    @Value("${strava.api.client.id}")
    private String clientId;

    @Value("${strava.api.client.secret}")
    private String clientSecret;

    @Value("${strava.api.refresh.token}")
    private String refreshToken;

    @Value("${strava.api.refresh.endpoint}")
    private String refreshEndpoint;

    @Value("${strava.api.segments.endpoint}")
    private String segmentsEndpoint;

    private String accessToken;

    @PostConstruct
    public void init() {
        logger.info("Initializing StravaService");
        refreshAccessToken();
    }

    public StravaSegments getSegmentsInRegion(Region region) {
        logger.info("Fetching segments for region: " + region.toString());

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization", "Bearer " + accessToken);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(segmentsEndpoint)
            .queryParam("bounds", region.getBoundsAsDelimitedString());

        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<StravaSegments> response =
            restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, request, StravaSegments.class);

        // TODO: Handle error response

        return response.getBody();
    }

    @Scheduled(cron = "30 * * * *")
    public void refreshAccessToken() {
        logger.info("Refreshing Strava API access token...");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        StravaTokenRefreshRequest tokenRefreshRequest =
            new StravaTokenRefreshRequest(clientId, clientSecret, "refresh_token", refreshToken);

        HttpEntity<StravaTokenRefreshRequest> request = new HttpEntity<>(tokenRefreshRequest, headers);

        ResponseEntity<StravaTokenRefreshResponse> response =
            restTemplate.exchange(refreshEndpoint, HttpMethod.POST, request, StravaTokenRefreshResponse.class);

        // TODO: Handle error response

        accessToken = response.getBody().getAccessToken();
        refreshToken = response.getBody().getRefreshToken();

        logger.info("Finished refreshing Strava API access token");
    }
}
