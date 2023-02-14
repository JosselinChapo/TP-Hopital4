package hopital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hopital.exception.SecretaireException;
import hopital.model.Secretaire;
import hopital.repository.SecretaireRepository;


@Service
public class SecretaireService {
	
	@Autowired
	private SecretaireRepository secretaireRepo;
	

	public Secretaire create(Secretaire secretaire ) {
		checkNotNull(secretaire);
		if (secretaire.getId() != null) {
			throw new SecretaireException("id inconnu");
		}
		checkConstraint(secretaire);
		return secretaireRepo.save(secretaire);
	}

	private void checkConstraint(Secretaire secretaire) {
		if (secretaire.getLogin() == null || secretaire.getLogin().isEmpty()) {
			throw new SecretaireException("login obligatoire");
		}
		if (secretaire.getPassword() == null || secretaire.getPassword().isEmpty()) {
			throw new SecretaireException("password obligatoire");
		}
	}

	private void checkNotNull(Secretaire secretaire) {
		if (secretaire == null) {
			throw new SecretaireException("secretaire obligatoire");
		}
	}

	private void checkId(Integer id) {
		if (id == null) {
			throw new SecretaireException("id null");
		}
	}
	private void checkExist(Secretaire secretaire) {
		checkId(secretaire.getId());
		findById(secretaire.getId());
	}

		public Secretaire findById(Integer id) {
			checkId(id);
			return secretaireRepo.findById(id).orElseThrow(SecretaireException::new);
		}

		public Secretaire update(Secretaire secretaire) {
			checkNotNull(secretaire);
			checkExist(secretaire);
			checkConstraint(secretaire);
			Secretaire secretaireEnBase = findById(secretaire.getId());
			secretaireEnBase.setLogin(secretaire.getLogin());
			secretaireEnBase.setPassword(secretaire.getPassword());
			
			// si pas d'adresse dans le secretaire recu on garde l'ancienne adresse

			return secretaireRepo.save(secretaireEnBase);
}

		public List<Secretaire> findAll() {
			return secretaireRepo.findAll();
		}

		public void delete(Secretaire secretaire) {
			checkExist(secretaire);
			secretaireRepo.delete(secretaire);
		}

		public void delete(Integer id) {
			delete(findById(id));
}

}
