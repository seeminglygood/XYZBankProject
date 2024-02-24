package objectData;

import java.util.HashMap;

public class AddCustomerObject {
    private String firstNameValue;
    private String lastNameValue;
    private String postCodeValue;

    public AddCustomerObject(HashMap<String, String> testData) {
        populateObject(testData);
    }

    private void populateObject(HashMap<String, String> testData) {
        for (String Key : testData.keySet()) {
            switch (Key) {
                case "firstNameValue":
                    setFirstNameValue(testData.get(Key));
                    break;
                case "lastNameValue":
                    setLastNameValue(testData.get(Key));
                    break;

                case "postCodeValue":
                    setPostCodeValue(testData.get(Key));
                    break;
            }
        }
    }

        public String getFirstNameValue () {
            return firstNameValue;
        }

        public void setFirstNameValue (String firstNameValue){
            this.firstNameValue = firstNameValue;
        }

        public String getLastNameValue () {
            return lastNameValue;
        }

        public void setLastNameValue (String lastNameValue){
            this.lastNameValue = lastNameValue;
        }

        public String getPostCodeValue () {
            return postCodeValue;
        }

        public void setPostCodeValue (String postCodeValue){
            this.postCodeValue = postCodeValue;
        }
    }

