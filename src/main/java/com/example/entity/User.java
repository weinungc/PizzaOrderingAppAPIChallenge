package com.example.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class User {

	@Id
	 public ObjectId _id;
    private String username;
    public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

    public User() {
    }

    public User(ObjectId userId, String userName, String password) {
        this._id = userId;
        this.username = userName;
        this.password = password;
    }


//    @Override
//    public String toString() {
//        return "Users{" + "userId=" + userId + ", userName=" + userName + ", password=" + password + '}';
//    }

}
