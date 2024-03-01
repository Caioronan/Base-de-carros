
public class car {
    

    int id;         //id
    boolean lapide;
    float price;    //float
    String date;    //data
    String body;    //string variavel
    String make;    //string fixa
    String model;   //string com separador
    
    @Override
    public String toString() {    
        return "Carro [id=" + id + ", price=" + price + ", date=" + date + ", body=" + body + ", make=" + make
                + ", model=" + model + "]";
    }
    public car(int id, float price, String date, String body, String make, String model) {
        this.id = id;
        this.lapide = false;
        this.price = price;
        this.date = date;
        this.body = body;
        this.make = make;
        this.model = model;
    }
    public car() {
        this.id = -1;
        this.lapide = false;
        this.price = -1;
        this.date = null;
        this.body = "";
        this.make = "";
        this.model = "";
    }

    
    
    
}
