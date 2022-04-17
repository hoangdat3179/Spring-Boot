package vn.techmaster.JobHunt.respository;

import org.springframework.stereotype.Repository;
import vn.techmaster.JobHunt.model.Employer;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EmployerRepo {
    private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();

    public EmployerRepo() {

    }

    // Sinh ra UUID
    public Employer add(Employer employer) {
        String id = UUID.randomUUID().toString();
        employer.setId(id);
        employers.put(id, employer);
        return employer;
    }

    public Collection<Employer> getAll() {
        return employers.values();
    }

    public Employer findById(String id) {
        return employers.get(id);
    }

    // Cập nhật logo employer
    public void updateLogo(String id, String logo_path) {
        var emp = employers.get(id);
        emp.setLogo_path(logo_path);
        employers.put(id, emp);
    }

    public Employer deleteById(String id) {
        return employers.remove(id);
    }

    @PostConstruct
    public void addSomeData() {
        this.add(Employer.builder().name("FPT").website("https://fpt.com.vn").logo_path("fpt.png")
                .email("hr@fpt.com.vn").build());
        this.add(Employer.builder().name("CMC").website("https://cmc.com.vn").logo_path("cmc.png")
                .email("hr@cmc.com.vn").build());
        this.add(Employer.builder().name("Amazon").website("https://amazon.com").logo_path("amazon.png")
                .email("hr@amazon.com.vn").build());
        this.add(Employer.builder().name("Google").website("https://google.com.vn").logo_path("googlelogo.png")
                .email("hr@google.com.vn").build());
    }
}
