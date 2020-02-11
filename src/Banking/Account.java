package Banking;

public class Account {
    private String name, address, updated_date;
    private int amount, ac_no;

    public int getAc_no(){return ac_no;}
    public void setAc_no(int ac_no) {this.ac_no = ac_no;}

    public int getAmount() {return amount;}
    public void setAmount(int amount) {this.amount = amount;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getUpdated_date(){return updated_date;}
    public void setUpdated_date(String updated_date) {this.updated_date = updated_date;}
}
