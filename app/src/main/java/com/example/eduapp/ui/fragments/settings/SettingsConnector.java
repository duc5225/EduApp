package com.example.eduapp.ui.fragments.settings;

import androidx.annotation.NonNull;

import com.example.eduapp.base.connector.BaseConnector;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.Transaction;
import com.example.eduapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsConnector extends BaseConnector {
  public void logout() {
    mAuth.signOut();
  }

  public void editProfile(User user, OnCompleted<String> onCompleted) {
    mDatabase.getReference("Users").child(mAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(task1 -> {
      if (task1.isSuccessful()) onCompleted.onFinish("Sửa profile thành công");
      else onCompleted.onError("failure add info:" + task1.getException());
    });
  }

  public void getUserTransaction(OnCompleted<List<Transaction>> onCompleted) {
    String userId = mAuth.getCurrentUser().getUid();
    mDatabase.getReference("Users").child(userId).child("transaction").orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        List<Transaction> transactions = new ArrayList<>();
        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
          Transaction transaction = postSnapshot.getValue(Transaction.class);
          transactions.add(transaction);
        }
        onCompleted.onFinish(transactions);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }
}
