package vn.techmasterr.jobhunt.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vn.techmasterr.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
    private ArrayList<Employer> employers;

    public EmployerRepository() {
        employers = new ArrayList<>(List.of(
                new Employer("Cường", "usa.gov", "thaisell0@tumblr.com", "5770 Coolidge Park"),
                new Employer("John", "princeton.edu", "aboeck3@reverbnation.com", "085 Johnson Parkway"),
                new Employer("Lara", "cbsnews.com", "cmcshirie4@wunderground.com", "3998 Columbus Center"),
                new Employer("Anna", "narod.ru", "cpates2@narod.ru", "791 Mifflin Lane"),
                new Employer("Liên", "bloglines.com", "stitman6@sourceforge.net", "8253 Hayes Drive"),
                new Employer("Ziang", "sina.com.cn", "ajennemann1@rakuten.co.jp", "57967 Bartelt Drive"),
                new Employer("Persie", "dion.ne.jp", "abromidge5@parallels.com", "67 Golden Leaf Plaza")));

    }

    public List<Employer> getEmployer() {
        return employers;
    }
}
