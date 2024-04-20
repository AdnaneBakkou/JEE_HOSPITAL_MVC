package ma.bakkou.hospitalmvcp1;

import ma.bakkou.hospitalmvcp1.entities.Patient;
import ma.bakkou.hospitalmvcp1.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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

   // @Bean
CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder=passwordEncoder();

        return args -> {
            UserDetails u1= jdbcUserDetailsManager.loadUserByUsername("user11");
     if (u1==null)  jdbcUserDetailsManager.createUser(
                User.withUsername("user11").password(passwordEncoder.encode("12345678")).roles("USER").build()
        );
            UserDetails u2= jdbcUserDetailsManager.loadUserByUsername("user22");

            if (u2==null)   jdbcUserDetailsManager.createUser(
                    User.withUsername("user22").password(passwordEncoder.encode("12345678")).roles("ADMIN").build()
            );
            UserDetails u3= jdbcUserDetailsManager.loadUserByUsername("user33");

            if (u3==null)  jdbcUserDetailsManager.createUser(
                    User.withUsername("user33").password(passwordEncoder.encode("12345678")).roles("USER","ADMIN").build()
            );

        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}