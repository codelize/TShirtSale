package br.com.fiap.apitshirtsale.domain.TShirt;

import br.com.fiap.apitshirtsale.exception.TShirtNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TShirtService {

    private final TShirtRepository TShirtRepository;

    public TShirtService(TShirtRepository TShirtRepository) {
        this.TShirtRepository = TShirtRepository;
    }

    public List<TShirt> getAllTShirts() {
        return TShirtRepository.findAll();
    }

    public TShirt getTShirtById(Long id) {
        return TShirtRepository.findById(id).orElseThrow(
                () -> new TShirtNotFoundException(id)
        );

    }

}
