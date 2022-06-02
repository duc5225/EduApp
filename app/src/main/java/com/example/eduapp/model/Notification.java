package com.example.eduapp.model;

import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Notification {
  private String id = UUID.randomUUID().toString();
  private Integer type;
  private NotiExParam exParam;

  public Notification(){
  }

  public Notification(Integer type, NotiExParam notiExParam) {
    this.type = type;
    this.exParam = notiExParam;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public NotiExParam getExParam() {
    return exParam;
  }

  public void setExParam(NotiExParam exParam) {
    this.exParam = exParam;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle(){
    switch (type){
      case 1:
        return "Nạp tiền thành công.";
      case 2:
        return "Rút tiền thành công.";
      case 3:
        return "Tạo lớp thành công";
      case 4:
        return "Lớp đã được nhận" ;
      case 5:
        return "Nhận lớp thành công";
      default:
        return "Lỗi";
    }
  }
  public String getMessage(){
    switch (type){
      case 1:
        return "Nạp tiền thành công. Số tiền bạn nạp là " + exParam.getAmount() + " token (" + (exParam.getAmount() * 1000) + "vnđ) đã được cộng vào tài khoản";
      case 2:
        return "Rút tiền thành công. Số tiền bạn rút là " + exParam.getAmount() + " token (" + (exParam.getAmount() * 1000) + "vnđ) đã bị trừ đi trong tài khoản";
      case 3:
        return "Tạo lớp thành công. Tên lớp học là: " + exParam.getClassName();
      case 4:
        return "Lớp học '" + exParam.getClassName() + "' của bạn đã được nhận. Số tiền nhận lớp " + exParam.getAmount() + " token (" + (exParam.getAmount() * 1000) + "vnđ) đã bị trừ đi trong tài khoản của bạn" ;
      case 5:
        return "Nhận lớp '"+ exParam.getClassName() + "' thành công. Số tiền nhận lớp " + exParam.getAmount() + " token (" + (exParam.getAmount() * 1000) + "vnđ) đã được cộng vào tài khoản";
      default:
        return "Thông báo lỗi";
    }
  }

  public Map<String, Object> toMap() {
    HashMap<String, Object> result = new HashMap<>();
    result.put("type", type);
    result.put("exParam", exParam.toMap());
    result.put("timestamp", ServerValue.TIMESTAMP);

    return result;
  }
}
