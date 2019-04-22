package dev.georgeblack.pathtorun.model.strava;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StravaTokenRefreshResponse {
  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("expires_at")
  private int expirationTimestamp;

  @JsonProperty("expires_in")
  private int secondUntilExpiration;

  @JsonProperty("refresh_token")
  private String refreshToken;
}
