package au.com.telstra.simcardactivator.repositories;


import au.com.telstra.simcardactivator.entity.SimCard;
import org.springframework.data.repository.CrudRepository;

public interface SimCardRepository extends CrudRepository<SimCard, Long> {}