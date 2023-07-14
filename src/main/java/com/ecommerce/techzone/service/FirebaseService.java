package com.ecommerce.techzone.service;

import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    public void sendOTP(String phoneNumber) throws FirebaseAuthException {
        FirebaseAuth.getInstance().generateSignInWithEmailLink(phoneNumber, getActionCodeSettings());
    }

    public void verifyOTP(String idToken) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        // Perform further verification logic
    }

    private ActionCodeSettings getActionCodeSettings() {
        return ActionCodeSettings.builder()
                .setUrl("https://example.com/verifyEmail")
                .setHandleCodeInApp(true)
                .setAndroidPackageName("com.example.app")
                .setIosBundleId("com.example.app")
                .build();
    }
}

