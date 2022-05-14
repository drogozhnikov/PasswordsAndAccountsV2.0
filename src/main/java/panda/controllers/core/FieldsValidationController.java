package panda.controllers.core;

import panda.models.Account;

public class FieldsValidationController {

    private final int nameSize = 90;
    private final int ownerSize = 50;
    private final int linkSize = 500;
    private final int mailSize = 100;
    private final int accountSize = 100;
    private final int passwordSize = 50;
    private final int descriptionSize = 500;

    public String validate(Account account){
        StringBuilder resultMessage = new StringBuilder();

            String emptyMessage = validateEmptyOrNull(account);
                if(emptyMessage.length()>0){
                    resultMessage.append("Found filling problems with: ");
                    resultMessage.append(emptyMessage);
                }
            String overSizedMessage = validateLength(account);
                if(overSizedMessage.length()>0){
                    resultMessage.append("Found length problems with: ");
                    resultMessage.append(overSizedMessage);
                }
        if(resultMessage.toString().length()>0){
            return resultMessage.toString();
        }else {
            return "";
        }
    }

    public String validateLength(Account account){
        StringBuilder overSizedMessage = new StringBuilder();
            overSizedMessage.append(getLengthErrorText(account.getName(),"Name", nameSize));
            overSizedMessage.append(getLengthErrorText(account.getOwner(),"Owner", ownerSize));
            overSizedMessage.append(getLengthErrorText(account.getLink(),"Link",linkSize));
            overSizedMessage.append(getLengthErrorText(account.getMail(),"Mail",mailSize));
            overSizedMessage.append(getLengthErrorText(account.getAccount(),"Account",accountSize));
            overSizedMessage.append(getLengthErrorText(account.getPassword(),"Password",passwordSize));
            overSizedMessage.append(getLengthErrorText(account.getInfo(),"Description",descriptionSize));
        return overSizedMessage.toString();
    }

    public String validateEmptyOrNull(Account account){
        StringBuilder emptyMessage = new StringBuilder();
            emptyMessage.append(getEmptyOrNullText(account.getName(),"Name"));
            emptyMessage.append(getEmptyOrNullText(account.getOwner(),"Owner"));
            emptyMessage.append(getEmptyOrNullText(account.getLink(),"Link"));
            emptyMessage.append(getEmptyOrNullText(account.getMail(),"Mail"));
            emptyMessage.append(getEmptyOrNullText(account.getAccount(),"Account"));
            emptyMessage.append(getEmptyOrNullText(account.getPassword(),"Password"));
            emptyMessage.append(getEmptyOrNullText(account.getInfo(),"Description"));
        return emptyMessage.toString();
    }

    public String getEmptyOrNullText(String input, String fieldName){
        if(isEmptyOrNull(input)){
            return "-"+fieldName+"-";
        }
        return "";
    }

    private boolean isEmptyOrNull(String input){
        if(input == null || input.equals("")){
            return true;
        }
        return false;
    }

    public String getLengthErrorText(String inputText, String fieldName, int size){
        StringBuilder lengthErrorText = new StringBuilder();
        if(!isEmptyOrNull(inputText) && inputText.length()>size){
                lengthErrorText.append(fieldName + ": field must contain no more than " + size + " characters" + "\n");
        }
       return lengthErrorText.toString();
    }
}
