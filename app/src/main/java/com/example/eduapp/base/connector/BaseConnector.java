package com.example.eduapp.base.connector;

import androidx.annotation.NonNull;

import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.itf.RetrofitConnector;
import com.example.eduapp.model.City;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.Notification;
import com.example.eduapp.model.Subject;
import com.example.eduapp.model.Transaction;
import com.example.eduapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseConnector {
  private static final String baseUrl = "https://eduapp-f8af5-default-rtdb.asia-southeast1.firebasedatabase.app";
  public final FirebaseAuth mAuth = FirebaseAuth.getInstance();
  public final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance(baseUrl);
  protected Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
  protected RetrofitConnector retrofitConnector = retrofit.create(RetrofitConnector.class);

  public BaseConnector() {
  }

  public void setTaken(String classId, String userId, OnCompleted onCompleted){
    Map<String, Object> map = new HashMap<>();
    map.put("taken", userId);
    mDatabase.getReference("Class")
        .child(classId).updateChildren(map).addOnCompleteListener(task1 -> {
      if (task1.isSuccessful()) onCompleted.onFinish("Nhận lớp thành công");
      else onCompleted.onError("failure take class:" + task1.getException());
    });
  }
  public void setNotification(String userId, Notification notification) {
    Map<String, Object> postValues = notification.toMap();
    Map<String, Object> childUpdates = new HashMap<>();
    childUpdates.put("/notification/" + notification.getId(), postValues);
    mDatabase.getReference("Users").child(userId).updateChildren(childUpdates);
  }

  public void setToken(String userId, Transaction transaction, Integer currentToken) {
    mDatabase.getReference("Users").child(userId).child("token").setValue(transaction.getAmount() + currentToken).addOnCompleteListener(new OnCompleteListener<Void>() {
      @Override
      public void onComplete(@NonNull Task<Void> task) {
        Map<String, Object> postValues = transaction.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/transaction/" + transaction.getId(), postValues);
        mDatabase.getReference("Users").child(userId).updateChildren(childUpdates);
      }
    });
  }

  public void getToken(String userId, OnCompleted<Integer> onCompleted) {
    mDatabase.getReference("Users").child(userId).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Integer token = snapshot.getValue(Integer.class);
        if (token == null) token = 0;
        onCompleted.onFinish(token);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError("Cancelled");
      }
    });
  }

  public void setClassLove(String classId) {
    String userId = mAuth.getCurrentUser().getUid();
    mDatabase.getReference("Users").child(userId).child("loveClassList").child(classId).setValue(true);
  }
  public void removeClassLove(String classId) {
    String userId = mAuth.getCurrentUser().getUid();
    mDatabase.getReference("Users").child(userId).child("loveClassList").child(classId).removeValue();
  }
  public void getClassLove(String classId, OnCompleted<Boolean> onCompleted){
    String userId = mAuth.getCurrentUser().getUid();
    mDatabase.getReference("Users").child(userId).child("loveClassList").child(classId).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Boolean user = snapshot.getValue(Boolean.class);
        onCompleted.onFinish(user);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void removeUserLove(String classId) {
    String userId = mAuth.getCurrentUser().getUid();
    mDatabase.getReference("Users").child(userId).child("loveUserList").child(classId).removeValue();
  }
  public void getUserLove(String userLoveId, OnCompleted<Boolean> onCompleted){
    String userId = mAuth.getCurrentUser().getUid();
    mDatabase.getReference("Users").child(userId).child("loveUserList").child(userLoveId).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Boolean user = snapshot.getValue(Boolean.class);
        onCompleted.onFinish(user);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }
  public void setUserLove(String userLove) {
    String userId = mAuth.getCurrentUser().getUid();
    mDatabase.getReference("Users").child(userId).child("loveUserList").child(userLove).setValue(true);
  }

  private void getListClassLove(String userId, OnCompleted<List<String>> onCompleted){
    mDatabase.getReference("Users").child(userId).child("loveClassList").addValueEventListener(new ValueEventListener() {
      @Override
      public void  onDataChange(DataSnapshot dataSnapshot) {
        Map<String, Boolean> classData = (Map<String,Boolean>) dataSnapshot.getValue();
        if (classData != null) onCompleted.onFinish(new ArrayList<>(classData.keySet()));
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void getAllClassLove(String userId, OnCompleted<List<Class>> onCompleted){
    getListClassLove(userId, object -> getAllClass(object1 -> {
      List<Class> classes = new ArrayList<>();
      for (String id : object) {
        for (Class aClass : object1) {
          if (aClass.getId().equals(id)) classes.add(aClass);
        }
      }
      onCompleted.onFinish(classes);
    }));
  }

  private void getListUserLove(String userId, OnCompleted<List<String>> onCompleted){
    mDatabase.getReference("Users").child(userId).child("loveUserList").addValueEventListener(new ValueEventListener() {
      @Override
      public void  onDataChange(DataSnapshot dataSnapshot) {
        Map<String, Boolean> userData = (Map<String,Boolean>) dataSnapshot.getValue();
        if (userData != null) onCompleted.onFinish(new ArrayList<>(userData.keySet()));
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void getAllUserLove(String userId, OnCompleted<List<User>> onCompleted){
    getListUserLove(userId, object -> getAllUsers(object1 -> {
      List<User> users = new ArrayList<>();
      for (String id : object) {
        for (User user : object1) {
          if (user.getId().equals(id)) users.add(user);
        }
      }
      onCompleted.onFinish(users);
    }));
  }
  public void isStudent(OnCompleted<Boolean> onCompleted) {
    getCUserData(object -> { onCompleted.onFinish(object.isStudent());});
  }

  public void getCUserData(OnCompleted<User> onCompleted) {
    FirebaseUser user = mAuth.getCurrentUser();
    DatabaseReference reference = mDatabase.getReference("Users");
    String userID = user.getUid();

    reference.child(userID).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        User user = snapshot.getValue(User.class);
        if (user == null) user = new User();
        onCompleted.onFinish(user);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }


  public String getCUid() {
    return mAuth.getCurrentUser().getUid();
  }

  public void getUser(String id, OnCompleted<User> onCompleted) {
    DatabaseReference reference = mDatabase.getReference("Users");
    reference.child(id).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        User subject = snapshot.getValue(User.class);
        onCompleted.onFinish(subject);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void getUserOnce(String id, OnCompleted<User> onCompleted) {
    DatabaseReference reference = mDatabase.getReference("Users");
    reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        User subject = snapshot.getValue(User.class);
        onCompleted.onFinish(subject);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void getSubject(String id, OnCompleted<Subject> onCompleted) {
    DatabaseReference reference = mDatabase.getReference("Subject");
    reference.child(id).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Subject subject = snapshot.getValue(Subject.class);
        onCompleted.onFinish(subject);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void getCity(String id, OnCompleted<City> onCompleted) {
    DatabaseReference reference = mDatabase.getReference("City");
    reference.child(id).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        City city = snapshot.getValue(City.class);
        onCompleted.onFinish(city);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void getClass(String id, OnCompleted<Class> onCompleted) {
    DatabaseReference reference = mDatabase.getReference("Class");
    reference.child(id).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Class aClass = snapshot.getValue(Class.class);
        onCompleted.onFinish(aClass);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void getAllClass(OnCompleted<List<Class>> onCompleted){
    DatabaseReference reference = mDatabase.getReference("Class");

    reference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        List<Class> classList = new ArrayList<>();
        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
          Class aClass = postSnapshot.getValue(Class.class);
            classList.add(aClass);
          }
        onCompleted.onFinish(classList);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void getAllUsers(OnCompleted<List<User>> onCompleted){
    DatabaseReference reference = mDatabase.getReference("Users");

    reference.addValueEventListener(new ValueEventListener() {
      List<User> users = new ArrayList<>();
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
          User user = postSnapshot.getValue(User.class);
          users.add(user);
        }
        onCompleted.onFinish(users);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  private void handleRequest(Call call, OnCompleted onCompleted) {
    call.enqueue(new Callback() {
      @Override
      public void onResponse(Call call, Response response) {
        if (!response.isSuccessful()) {
          onCompleted.onError("Error: " + response.code());
          return;
        }
        onCompleted.onFinish(response.body());
      }

      @Override
      public void onFailure(Call call, Throwable t) {
        onCompleted.onError(t.toString());
      }
    });
  }

  public void countSubjectClass(OnCompleted<Integer[]> onCompleted) {
    DatabaseReference reference = mDatabase.getReference("Class");

    reference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Integer[] count = {0, 0, 0, 0, 0, 0};
        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
          Class aClass = postSnapshot.getValue(Class.class);
          for (Integer i = 0; i < 6; i++) {
            if (aClass.getSubjectId().endsWith((i + 1) + "")) {
              count[i]++;
            }
          }
        }
        onCompleted.onFinish(count);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void countSubjectUser(OnCompleted<Integer[]> onCompleted) {
    DatabaseReference reference = mDatabase.getReference("Users");

    reference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Integer[] count = {0, 0, 0, 0, 0, 0};
        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
          User user = postSnapshot.getValue(User.class);
          if (user.getSubjectList() != null) for (String string : user.getSubjectList()) {
            for (Integer i = 0; i < 6; i++) {
              if (string.endsWith((i + 1) + "")) {
                count[i]++;
              }
            }
          }
        }
        onCompleted.onFinish(count);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void countCityClass(OnCompleted<Integer[]> onCompleted) {
    DatabaseReference reference = mDatabase.getReference("Class");

    reference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Integer[] count = {0, 0, 0, 0, 0};
        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
          Class aClass = postSnapshot.getValue(Class.class);
          for (Integer i = 0; i < 5; i++) {
            if (aClass.getCityId().endsWith((i + 1) + "")) {
              count[i]++;
            }
          }
        }
        onCompleted.onFinish(count);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void countCityUser(OnCompleted<Integer[]> onCompleted) {
    DatabaseReference reference = mDatabase.getReference("Users");

    reference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Integer[] count = {0, 0, 0, 0, 0};
        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
          User user = postSnapshot.getValue(User.class);
          for (Integer i = 0; i < 5; i++) {
            if (user.getCityId().endsWith((i + 1) + "") && !user.isStudent()) {
              count[i]++;
            }
          }
        }
        onCompleted.onFinish(count);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void removeTaken(String classId, String takenId) {
    DatabaseReference reference = mDatabase.getReference("Class");
    reference.child(classId).child("taken").setValue("");
  }

  public void deleteClass(String id) {
    DatabaseReference reference = mDatabase.getReference("Class");
    reference.child(id).removeValue();
  }
}
