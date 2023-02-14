package hopital.test;

import java.time.LocalDate;

import hopital.config.AppConfig;
import hopital.repository.MedecinRepository;
import hopital.repository.PatientRepository;
import hopital.repository.SecretaireRepository;
import hopital.repository.VisiteRepository;
import org.junit.jupiter.api.Test;

import hopital.model.Medecin;
import hopital.model.Patient;
import hopital.model.Secretaire;
import hopital.model.Visite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(AppConfig.class)
@Transactional
public class HopitalTest {

	@Autowired private MedecinRepository medecinRepository;
	@Autowired private PatientRepository patientRepository;
	@Autowired private SecretaireRepository secretaireRepository;
	@Autowired private VisiteRepository visiteRepository;

	@Test
	public void loadData() {

		Medecin docBrown = new Medecin("docbrown@backtothefuture.com", "doloreane");
		medecinRepository.save(docBrown);

		Secretaire laSecretaire = new Secretaire("secret@backtothefuture.com", "Password");
		secretaireRepository.save(laSecretaire);

		Patient marty = new Patient("MacFly", "Marty");
		patientRepository.save(marty);

		Visite visiteMarty = new Visite(marty, docBrown, 30, 1, LocalDate.now());
		visiteRepository.save(visiteMarty);

	}

}
