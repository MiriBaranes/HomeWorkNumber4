public class User {
        private String userName;
        private String password;
        private String phoneNumber;
        private boolean isMediator;

        public User(String userName,String password,String phoneNumber, boolean isAMediator){
            this.userName=userName;
            this.password=password;
            this.phoneNumber=phoneNumber;
            this.isMediator =isAMediator;
        }
        public User(){
            this.userName=null;
            this.password=null;
            this.phoneNumber=null;
            this.isMediator=false;
        }
        public String getUserName(){
            return this.userName;
        }
        public String getPassword(){
            return this.password;
        }
        public void setPassword(String password){
            this.password=password;
        }
        public String getPhoneNumber(){
            return this.phoneNumber;
        }
        public void setPhoneNumber(String phoneNumber){
            this.phoneNumber=phoneNumber;
        }

        public boolean isMediator() {
            return isMediator;
        }

        public void setMediator(boolean mediator) {
            isMediator = mediator;
        }

        public String toString (){
            StringBuilder output=new StringBuilder();
            output.append("Username: ").append(this.userName).append(" phone number: ").append(this.phoneNumber);
            if (this.isMediator) {
                output.append(" (Mediator)");
            }
            else {
                output.append(" (Ordinary user)");
            }
            return output.toString();
        }
    }
