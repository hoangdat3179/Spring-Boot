package vn.techmaster.job_hunt.request;

import vn.techmaster.job_hunt.model.City;

public record SearchRequest(String keyword, City city) {
}
