public class Address {
        private String cityName;
        public String streetName;

        public Address(String cityName,String streetName){
            this.cityName=cityName;
            this.streetName=streetName;
        }
        public String getCityName(){
            return this.cityName;
        }
        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
        public String getStreetName(){
            return this.streetName;
        }
        public void setStreetName(String streetName){
            this.streetName = streetName;
        }
        public String toString (){
            StringBuilder output= new StringBuilder();
            output.append("name of the city: ").append(this.cityName).append(" name street: ").append(this.streetName);
            return output.toString();
        }
    }
