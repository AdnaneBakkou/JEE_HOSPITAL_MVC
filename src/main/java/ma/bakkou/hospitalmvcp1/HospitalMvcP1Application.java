package ma.bakkou.hospitalmvcp1;

import ma.bakkou.hospitalmvcp1.entities.Patient;
import ma.bakkou.hospitalmvcp1.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class HospitalMvcP1Application implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HospitalMvcP1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        //patientRepository.save(new Patient(null,"adnane",new Date(),false,55));
        //      patientRepository.save(new Patient(null,"moe",new Date(),false,885));
        //    patientRepository.save(new Patient(null,"meryem",new Date(),true,102));


    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}