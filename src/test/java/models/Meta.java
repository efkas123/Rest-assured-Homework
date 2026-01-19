package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Meta {

    @JsonProperty("powered_by")
    private String poweredBy;
    @JsonProperty("upgrade_url")
    private String upgradeUrl;
    @JsonProperty("example_url")
    private String exampleUrl;
    private String variant;
    private Cta cta;
    private String context;
    @JsonProperty("docs_url")
    private String docsUrl;
    @JsonProperty("template_gallery")
    private String templateGallery;
    private String message;
    private List<String> features;
    @JsonProperty("upgrade_cta")
    private String upgradeCta;
}
