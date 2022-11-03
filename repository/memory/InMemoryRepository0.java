package socialnetwork.repository.memory;

import socialnetwork.domain.Entity;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.Repository0;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository0<ID, E extends Entity<ID>> implements Repository0<ID,E> {


    private int id;
    private Validator<E> validator;
    Map<ID,E> entities;

    public InMemoryRepository0(Validator<E> validator) {
        this.validator = validator;
        entities=new HashMap<ID,E>();
        id=0;
    }

    @Override
    public E findOne(ID id){
        if (id==null)
            throw new IllegalArgumentException("\u001B[31m"+"id must be not null"+"\u001B[0m");
        if(entities.get(id)==null){
            throw new IllegalArgumentException("\u001B[31m"+"ID inexistent"+"\u001B[0m");
        }
        else
             return entities.get(id);
    }


    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    public  Map<ID,E> getALl(){
        return entities;
    }
    @Override
    public E save(E entity) {
        if (entity==null)
            throw new IllegalArgumentException("\u001B[31m"+"entity must be not null"+"\u001B[0m");
        try {
            validator.validate(entity);
            if(entities.get(entity.getId()) != null) {
                return entity;
            }
            else {
                entities.put(entity.getId(),entity);
                // entities.put(id,entity);
                // id++;
            }
        }
        catch (ValidationException ve){
            System.out.println("\u001B[31m"+ve.toString()+"\u001B[0m");
        }


        return null;
    }

    @Override
    public E delete(ID id) {
        E entity=findOne(id);
        entities.remove(id);
        return entity;
    }

    @Override
    public E update(E entity) {

        if(entity == null)
            throw new IllegalArgumentException("\u001B[31m"+"entity must be not null!"+"\u001B[0m");
        validator.validate(entity);

        entities.put(entity.getId(),entity);

        if(entities.get(entity.getId()) != null) {
            entities.put(entity.getId(),entity);
            return null;
        }
        return entity;

    }




}
