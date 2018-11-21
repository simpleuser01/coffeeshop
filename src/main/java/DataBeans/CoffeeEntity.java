package DataBeans;

import javax.persistence.*;

@Entity
@Table(name = "coffeeshop")
public class CoffeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int price;
    private int amount;
    private String delivery;
    private String date;
    private String timefrom;
    private String timeto;
    private int sum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimefrom() {
        return timefrom;
    }

    public void setTimefrom(String timefrom) {
        this.timefrom = timefrom;
    }

    public String getTimeto() {
        return timeto;
    }

    public void setTimeto(String timeto) {
        this.timeto = timeto;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Coffee toDTO(){
        Coffee coffee = new Coffee();
        coffee.setId(this.id);
        coffee.setName(this.name);
        coffee.setPrice(this.price);
        coffee.setAmount(this.amount);
        coffee.setDate(this.date);
        coffee.setTimefrom(this.timefrom);
        coffee.setTimeto(this.timeto);
        coffee.setDelivery(this.delivery);
        coffee.setSum(this.sum);
        return coffee;
    }

    public void fromDTO(Coffee coffee){
        this.setId(coffee.getId());
        this.setName(coffee.getName());
        this.setPrice(coffee.getPrice());
        this.setAmount(coffee.getAmount());
        this.setDate(coffee.getDate());
        this.setTimefrom(coffee.getTimefrom());
        this.setTimeto(coffee.getTimeto());
        this.setDelivery(coffee.getDelivery());
        this.setSum(coffee.getSum());
    }
}
