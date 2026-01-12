package models;

import lombok.Data;

import java.util.List;

@Data
public class Meta {
private String powered_by;
private String upgrade_url;
private String example_url;
private String variant;
private Cta cta;
private String context;
private String docs_url;
private String template_gallery;
private String message;
private List<String> features;
private String upgrade_cta;
}
