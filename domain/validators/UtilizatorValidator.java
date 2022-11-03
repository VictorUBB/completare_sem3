package socialnetwork.domain.validators;

import socialnetwork.domain.Utilizator;

import java.util.Objects;

public class UtilizatorValidator implements Validator<Utilizator> {
    @Override
    public void validate(Utilizator entity) throws ValidationException {
        //TODO: implement method validate
        if(Objects.equals(entity.getFirstName(), "")){
            throw new ValidationException("Numele utilizatorului este null");
        }
        if(Objects.equals(entity.getLastName(), "")){
            throw new ValidationException("Prenumele utilizatoruli este null");
        }

    }
}
