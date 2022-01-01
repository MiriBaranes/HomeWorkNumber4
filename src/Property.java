public class Property {
    public static final int APARTMENT_IN_BUILDING=1;
    public static final int PENTHOUSE_IN_BUILDING=2;
    public static final int PRIVAT_HOUSE=3;
    private Address address;
    private int numberOfRooms;
    private double price;
    private int typeOfApartment;
    private boolean forRent;
    private int houseNumber;
    private int floorNumber;
    private User advertiser;

    public Property(Address address,int numberOfRooms,double price, int typeOfApartment,boolean forRent,int houseNumber,int floorNumber,User advertiser){
        this.address=address;
        this.numberOfRooms=numberOfRooms;
        this.price=price;
        if (typeOfApartment<=PRIVAT_HOUSE&&typeOfApartment>=APARTMENT_IN_BUILDING) {
            this.typeOfApartment = typeOfApartment;
        }
        this.forRent=forRent;
        this.houseNumber=houseNumber;
        this.floorNumber=floorNumber;
        this.advertiser=advertiser;
    }
    public Address getAddress(){
        return this.address;
    }
    public void setAddress(Address adders){
        this.address=adders;
    }
    public int getNumberOfRooms(){
        return this.numberOfRooms;
    }
    public void setNumberOfRooms(int numberOfRooms){
        if (numberOfRooms >= 0) {
            this.numberOfRooms=numberOfRooms;
        }
        else {
            System.out.println("Invalid selection");
        }
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        if (price>=0) {
            this.price = price;
        }
        else {
            System.out.println("Invalid selection");
        }
    }
    public int getTypeOfApartment(){
        return this.typeOfApartment ;
    }
    public void setTypeOfApartment(int typeOfApartment) {
        if (typeOfApartment<=PRIVAT_HOUSE&&typeOfApartment>=APARTMENT_IN_BUILDING) {
            this.typeOfApartment = typeOfApartment;
        }
        else {
            System.out.println("Invalid selection");
        }
    }
    public boolean isForRent() {
        return forRent;
    }
    public void setForRent(boolean forRent){
        this.forRent=forRent;
    }
    public int getHouseNumber(){
        return this.houseNumber;
    }
    public void setHouseNumber(int houseNumber){
        if (houseNumber>0) {
            this.houseNumber = houseNumber;
        }
        else {
            System.out.println("Invalid selection");
        }
    }
    public int getFloorNumber(){
        return this.floorNumber;
    }
    public void setFloorNumber(int floorNumber){
        if (this.floorNumber>=0) {
            this.floorNumber = floorNumber;
        }else {
            System.out.println("Invalid selection");
        }
    }
    public User getAdvertiser(){
        return this.advertiser;
    }
    public void setAdvertiser(User advertiser){
        this.advertiser=advertiser;
    }
    public String toString (){
        StringBuilder output= new StringBuilder();
        if (this.typeOfApartment==APARTMENT_IN_BUILDING){
            output.append("Ordinary apartment in an apartment building");
        }
        else if (this.typeOfApartment==PENTHOUSE_IN_BUILDING){
            output.append("Penthouse in an apartment building ");
        }
        else if (this.typeOfApartment==PRIVAT_HOUSE){
            output.append("private house ");
        }
        if (this.forRent){
            output.append(" For rent: ");
        }
        else {
            output.append(" For Sale: ");
        }
        output.append(this.numberOfRooms).append(" rooms, ");
        if (this.typeOfApartment!=PRIVAT_HOUSE) {
            output.append(" floor: ").append(this.floorNumber);
        }
        output.append("\nPrice: ").append(this.price).append("$").append("\nContact info: ").append(this.advertiser);

        return output.toString();
    }
}
