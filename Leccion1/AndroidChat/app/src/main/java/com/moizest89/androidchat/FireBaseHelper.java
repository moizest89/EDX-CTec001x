package com.moizest89.androidchat;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by @moizest89 in SV on 7/12/16.
 */
public class FireBaseHelper {

    private Firebase dataReference;
    private final static String SEPARATOR = "__________";
    private final static String CHATS_PATH = "chats";
    private final static String USERS_PATH = "users";
    private final static String CONTACTS_PATH = "contacts";
    private final static String FINAL_PATH = "https://edx-ctec001x-firebasetes-8ccd1.firebaseio.com/";

    private final static String PROVIDER_DATA_EMAIL = "email";

    private final static String CHAT_STATUS_LABEL = "online";

    private static class SingeltonHelper{
        private final static FireBaseHelper INSTANCE = new FireBaseHelper();
    }


    public static FireBaseHelper getInstance(){
        return  SingeltonHelper.INSTANCE;
    }

    public FireBaseHelper() {
        this.dataReference = new Firebase(FINAL_PATH);
    }


    public Firebase getDataReference() {
        return dataReference;
    }

    public String getAuthUserEmail(){
        AuthData authData = dataReference.getAuth();

        String mEmail = null;

        if (authData != null){
            Map<String, Object> proverData = authData.getProviderData();
            mEmail = proverData.get(PROVIDER_DATA_EMAIL).toString();
        }

        return mEmail;
    }


    public Firebase getUserReference(String email){
        Firebase userReference = null;
        if (email != null){
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }


    public Firebase getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }


    public Firebase getContactsReference(String email){
        return getUserReference(email).child(CONTACTS_PATH);
    }

    public Firebase getMyContacsReference(){
        return getContactsReference(getAuthUserEmail());
    }

    public Firebase getOneContactReference(String mainEmail, String childEmail){
        String childKey = childEmail.replace(".","_");
        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
    }


    public Firebase getChatsReferences(String receiver){
        String keySender = getAuthUserEmail().replace(".","_");
        String keyreceiver = receiver.replace(".", "_");

        String keyChat = keySender + SEPARATOR + keyreceiver;

        if(keySender.compareTo(keyreceiver) > 0){
            keyChat = keyreceiver + SEPARATOR + keySender;
        }

        return dataReference.getRoot().child(CHATS_PATH).child(keyChat);
    }


    public void changeUserConnectionStatus(boolean online){

        if(getMyUserReference() != null){
            Map<String,Object> updates = new HashMap<>();
            updates.put(CHAT_STATUS_LABEL, online);
            getMyUserReference().updateChildren(updates);
            notifyContactsOfConnectionChange(online);
        }

    }

    public void notifyContactsOfConnectionChange(boolean online) {
        notifyContactsOfConnectionChange(online, false);
    }

    public void notifyContactsOfConnectionChange(final boolean online, final boolean signoff) {

        final String myEmail = getAuthUserEmail();
        getMyContacsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    String mEmail = data.getKey();
                    Firebase reference = getOneContactReference(mEmail,myEmail);
                    reference.setValue(online);
                }

                if(signoff){
                    dataReference.unauth();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }

    public void signOff(){
        notifyContactsOfConnectionChange(false, true);
    }

}
