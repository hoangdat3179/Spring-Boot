package vn.techmaster.demo_jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vn.techmaster.demo_jpa.model.Job;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TestJob {
    @Autowired private EntityManager em;

    @Test
    public void addJob(){
        Job job = Job.builder().title("Java Developer").description("Kinh nghiệm 12 tháng").build();
        em.persist(job);
        System.out.println(job.getId());
        assertThat(job.getId()).isNotNull();
        assertThat(job.getId().toString()).hasSizeGreaterThan(10);

    }
}
