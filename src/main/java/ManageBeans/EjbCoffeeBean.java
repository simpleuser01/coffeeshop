package ManageBeans;

import DataBeans.Coffee;
import DataBeans.CoffeeEntity;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Local
@Stateless
public class EjbCoffeeBean {

    @PersistenceContext(unitName = "coffeeshop")
    EntityManager entityManager;

    public List<Coffee> readList(){
     /*   if(offset<0 || limit<1){
            return Collections.emptyList();
        }*/
        TypedQuery<CoffeeEntity> query =
                entityManager.createQuery("select entity from CoffeeEntity entity",
                        CoffeeEntity.class);
        /*query.setFirstResult(offset);
        query.setMaxResults(limit);*/
        List<CoffeeEntity> productsEntities = query.getResultList();
        if(productsEntities == null || productsEntities.isEmpty()){
            return Collections.emptyList();
        }
        List<Coffee> products = new ArrayList<Coffee>(productsEntities.size());
        for (CoffeeEntity coffeeEntity:productsEntities) {
            products.add(coffeeEntity.toDTO());
        }
        return products;
    }

    public boolean create(Coffee coffee){
        if(!chekValid(coffee)){
            return false;
        }
        CoffeeEntity existCoffee = entityManager.find(CoffeeEntity.class, coffee.getId());

        if(existCoffee != null){
            return false;
        }
        existCoffee = new CoffeeEntity();
        existCoffee.fromDTO(coffee);
        entityManager.persist(existCoffee);
        return true;
    }

    public boolean update(Coffee coffee){
        if(!chekValid(coffee)){
            return false;
        }
        CoffeeEntity existProduct = entityManager.find(CoffeeEntity.class, coffee.getId());
        if(existProduct == null){
            return false;
        }
        existProduct.fromDTO(coffee);
        entityManager.merge(existProduct);
        return true;
    }

    public Coffee read(int id){
        CoffeeEntity productsEntity = entityManager.find(CoffeeEntity.class, id);
        if(productsEntity==null) {
            return null;
        }
        return productsEntity.toDTO();
    }

    public boolean  delete (int id){
        CoffeeEntity deleteEntity = entityManager.find(CoffeeEntity.class, id);
        if(deleteEntity == null){
            return  false;
        }
        entityManager.remove(deleteEntity);
        return true;
    }

    private boolean chekValid(Coffee coffee){
        //put in validator
        return!(coffee==null ||
                StringUtils.isEmpty(coffee.getName()) ||
                coffee.getPrice() <=0);
    }


}
