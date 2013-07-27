package com.ormanli.primephonebook;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.File;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;

public class User {

	private String id;
	private String name;
	private String surname;
	private String phonenumber;
	private byte[] image = new byte[]{12};

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public User() {
	}

	public User(String name, String surname, String phonenumber, byte[] image) {
		this.name = name;
		this.surname = surname;
		this.phonenumber = phonenumber;
		this.image = image;

	}
}
