package ManageBeans;

import DataBeans.Coffee;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class CdiCoffeeBean  implements Serializable {

    Coffee newCoffee = new Coffee();
    Map<String, String> coffeeTypes = new HashMap<>();

    Coffee deleteCoffee;

    public Coffee getDeleteCoffee() {
        return deleteCoffee;
    }

    public void setDeleteCoffee(Coffee deleteCoffee) {
        this.deleteCoffee = deleteCoffee;
    }

    Coffee editingCoffee;

    @EJB
    EjbCoffeeBean ejbCoffeeBean;

    @PostConstruct
    public void initialize(){
        coffeeTypes.put("Jacobs", "Jacobs");
        coffeeTypes.put("Nescafe", "Nescafe");
    }

    public Coffee getNewCoffee() {
        return newCoffee;
    }

    public void setNewCoffee(Coffee newCoffee) {
        this.newCoffee = newCoffee;
    }

    public List<Coffee> getCoffeeList(){
        return  ejbCoffeeBean.readList();

    }

    public Map<String, String> getCoffeeTypes() {
        return coffeeTypes;
    }

    public void createCoffee(){
        ejbCoffeeBean.create(newCoffee);
        newCoffee = new Coffee();
    }
    public void delete(int id){
        ejbCoffeeBean.delete(id);
    }


    public void setEditingCoffee(Coffee editingCoffee) {
        this.editingCoffee = editingCoffee;
    }



    public Coffee getEditingCoffee() {
        return editingCoffee;
    }

    public void saveProduct(){
        ejbCoffeeBean.update(editingCoffee);
    }

    public void check() {
        //rewrite
        String name = newCoffee.getName();
        if (name.equals("Jacobs")) {
            newCoffee.setPrice(130);
        }
        if (name.equals("Nescafe")) {
            newCoffee.setPrice(110);
        }
    }

    public void chekSum() {
        int price = newCoffee.getPrice();
        int amount = newCoffee.getAmount();
        newCoffee.setSum((price * amount)/1000);

    }

    public void chekSelf() {

        boolean sam = newCoffee.isSelf();

        int sum = newCoffee.getSum();
        if (sam == true) {
            newCoffee.setSum(sum);
            newCoffee.setDelivery("Самовывоз");
        }
    }

    public void chekCourier() {
        boolean cour = newCoffee.isCourier();

        int sum = newCoffee.getSum();
        if (cour == true) {
            newCoffee.setSum(sum + 10);
            newCoffee.setDelivery("Курьер");
        }else {
            newCoffee.setSum(sum-10);
        }
    }

    public void edit() {
        String name = editingCoffee.getName();
        if (name.equals("Jacobs")) {
            editingCoffee.setPrice(130);
        }
        if (name.equals("Nescafe")) {
            editingCoffee.setPrice(110);
        }
    }

    public void editSum() {
        int price = editingCoffee.getPrice();
        int amount = editingCoffee.getAmount();
        editingCoffee.setSum((price * amount)/1000);

    }

    public void editSelf() {

        boolean sam = editingCoffee.isSelf();

        int sum = editingCoffee.getSum();
        if (sam == true) {
            editingCoffee.setSum(sum);
            editingCoffee.setDelivery("Самовывоз");
        }
    }

    public void editCourier() {
        boolean cour = editingCoffee.isCourier();

        int sum = editingCoffee.getSum();
        if (cour == true) {
            editingCoffee.setSum(sum + 50);
            editingCoffee.setDelivery("Курьер");
        }else {
            editingCoffee.setSum(sum-50);
        }
    }
}
